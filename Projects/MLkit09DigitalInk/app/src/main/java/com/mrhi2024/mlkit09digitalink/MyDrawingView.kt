package com.mrhi2024.mlkit09digitalink

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.google.android.material.card.MaterialCardView
import com.google.mlkit.vision.digitalink.Ink

class MyDrawingView(context: Context, attrs: AttributeSet?) : MaterialCardView(context, attrs) {

    // 터치 이벤트 종류
    var actionType:String= "no action"

    // 터치 좌표값
    var touchX:Float = 0.0f
    var touchY:Float = 0.0f

    // 터치 타입스탬프
    var touchTimeStamp: Long= 0

    // 손글씨를 그려내기 위한 터치좌표들 경로를 저장하는 객체 Path
    var path: Path= Path()

    // 디지털 잉크 윤곽선 빌더 객체 - 위 path와 비슷한 녀석
    lateinit var inkStrokeBuilder: Ink.Stroke.Builder
    var inkBuilder: Ink.Builder = Ink.builder()


    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)
        //파라미터 canvas : View 영역에 놓여진 도화지...여기에 그림을 그리면..View 영역에 보여짐.
        //그림을 그리는 도구 페인트 준비
        val paint= Paint()
        paint.color= Color.GRAY
        paint.textSize= 40f
        canvas.drawText("여기에 손글씨를 써보세요.", 40f, 100f, paint)

        // 이벤트 정보 그려내기..
        paint.color= Color.BLACK
        canvas.drawText("$actionType : $touchX, $touchY ~ $touchTimeStamp", 40f, height.toFloat()-40f, paint)

        // 손글씨 그리기..
        paint.color= Color.BLUE
        paint.style= Paint.Style.STROKE
        paint.strokeWidth= 8f

        canvas.drawPath(path, paint)

    }

    // 이 뷰가 터치관련 이벤트가 발생했을 때 자동으로 발동하는 콜백 메소드
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        //터치 이벤트 액션의 종류 분류..
        val action: Int= event!!.action
        //이벤트 발생 좌표
        val x: Float = event.x
        val y: Float = event.y
        //이벤트 발생 시간 - Digital Ink Recognition에서 필요한 값
        val timeStamp: Long = System.currentTimeMillis()

        when(action){
            MotionEvent.ACTION_DOWN -> touchDown(x,y,timeStamp)
            MotionEvent.ACTION_UP -> touchUp(x,y,timeStamp)
            MotionEvent.ACTION_MOVE -> touchMove(x,y,timeStamp)
        }

        // 이 뷰의 화면을 다시 그리도록 갱신하기!! - onDraw() 메소드가 다시 실행됨
        invalidate()

        return true //이게 true 여야만..move, up을 인식할 수 있음.
    }

    private fun touchDown(x:Float, y:Float, timeStamp:Long){
        actionType= "DOWN"
        touchX= x
        touchY= y
        touchTimeStamp= timeStamp

        path.moveTo(x, y)

        //손글씨 인식을 위해..좌표를 저장..
        inkStrokeBuilder= Ink.Stroke.builder()
        inkStrokeBuilder.addPoint( Ink.Point.create(x,y,timeStamp) )

    }

    private fun touchUp(x:Float, y:Float, timeStamp:Long){
        actionType= "UP"
        touchX= x
        touchY= y
        touchTimeStamp= timeStamp

        inkBuilder.addStroke(inkStrokeBuilder.build())
    }

    private fun touchMove(x:Float, y:Float, timeStamp:Long){
        actionType= "MOVE"
        touchX= x
        touchY= y
        touchTimeStamp= timeStamp

        path.lineTo(x, y)

        inkStrokeBuilder.addPoint( Ink.Point.create(x,y,timeStamp) )

    }

    //손글씨 분석 좌표를 가진 Ink객체를 만들어서 리턴해주는 메소드
    fun getInk():Ink{
        val ink:Ink = inkBuilder.build()
        return ink
    }


    //화면을 모두 지워는 기능 메소드
    fun clearView(){
        actionType= "no action"
        touchX=0.0f
        touchY=0.0f
        touchTimeStamp= 0
        path= Path()

        inkBuilder= Ink.builder()

        invalidate()
    }

}