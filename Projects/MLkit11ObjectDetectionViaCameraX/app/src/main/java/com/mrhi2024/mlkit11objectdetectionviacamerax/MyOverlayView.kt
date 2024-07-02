package com.mrhi2024.mlkit11objectdetectionviacamerax

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.google.mlkit.vision.objects.DetectedObject

class MyOverlayView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    //뷰가 화면에 그려내는 작업을 수행하는 콜백메소드
    override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)

        //파라미터 canvas : 이 MyOverlayView에 붙어있는 도화지..
        //동작 테스트
//        var paint= Paint().apply {
//            color=Color.BLUE
//            style=Paint.Style.STROKE
//            strokeWidth=8f
//            textSize= 80f
//        }
//        canvas.drawText("Hello", 100f, 100f, paint)

        // 영역 박스 그리기
        val paint= Paint().apply {
            style= Paint.Style.STROKE
            strokeWidth= 4f
            textSize=40f
        }
        val colors= arrayOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN)

        var n=0
        objectList?.forEach {
            val bounds:Rect= it.boundingBox
            // 비율을 반영
            bounds.left= (bounds.left * widthRatio).toInt()
            bounds.top = (bounds.top * heightRatio).toInt()

            bounds.right= (bounds.right * widthRatio).toInt()
            bounds.bottom= (bounds.bottom * heightRatio).toInt()

            paint.color= colors[n++]
            canvas.drawRect(bounds, paint)
            canvas.drawText("${it.trackingId}", bounds.left.toFloat(), bounds.top.toFloat(), paint)
        }

    }//onDraw method..

    private var objectList: List<DetectedObject>? = null
    private var widthRatio:Float= 1f
    private var heightRatio:Float= 1f


    fun drawObjectBoundingBox(objectList: List<DetectedObject>, widthRatio:Float, heightRatio:Float){
        this.objectList= objectList
        this.widthRatio= widthRatio
        this.heightRatio= heightRatio

        // 이 뷰의 화면 갱신 - onDraw() 메소드가 다시 실행되는 것임.
        postInvalidate()
    }

}//MyOverlayView class..