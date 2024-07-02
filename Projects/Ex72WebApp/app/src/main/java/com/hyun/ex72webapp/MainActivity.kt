package com.hyun.ex72webapp


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val wv: WebView by lazy { findViewById(R.id.wv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //wv.loadUrl("http://mrhi2024.dothome.co.kr")
        //wv.loadUrl("file:///android_asset/index.html")
        //wv.loadUrl("http://mrhi2024.dothome.co.kr/react/ex01/")
        //wv.loadUrl("http://mrhi2024.dothome.co.kr/react/movie/")
        wv.loadUrl("http://mrhi2024.dothome.co.kr/react/ex13/") // file 선택기는 웹뷰에서 동작하지 않음.. 사용자가 파일선택 이벤트를 발생하면 반응하기 - MyWebChromeClient

        //웹뷰는 기본적으로 JavaScript의 사용을 금지함. 보안문제
        //웹뷰에 설정
        wv.settings.javaScriptEnabled= true
        wv.settings.builtInZoomControls= true
        wv.settings.displayZoomControls= false
        wv.settings.allowFileAccess= true // http가 아닌 html에서도 ajax 동작 허용

        //다이얼로그 같은 alert()에 대한 동작을 허용하기 -- javascript의 특정 이벤트를 대응하는 객체가 필요
        //wv.webChromeClient= WebChromeClient()
        //웹뷰의 특정 이벤트에 대응할 수 있는 기능을 가진 WebChromeClient 의 기능을 개선하기..
        wv.webChromeClient= MyWebChromeClient(this)

        //웹뷰안에서 <a>같은 요소에 의해 새로운 페이지가 열릴때 디바이스의 웹브라우저에서 열림. 이를 방지하기
        wv.webViewClient= WebViewClient()
    }//onCreate method..

    override fun onBackPressed() {
        if(wv.canGoBack()) wv.goBack()
        else super.onBackPressed()
    }

    var mFilePathCallback: ValueCallback<Array<Uri>>?= null


    inner class MyWebChromeClient(val context:Context) : WebChromeClient(){

        //웹뷰의 input type=file 요소를 선택했을 때 반응하기
        override fun onShowFileChooser(
            webView: WebView?,
            filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            Toast.makeText(context, "파일 선택 클릭 이벤트 발생", Toast.LENGTH_SHORT).show()

            //사진 선택 화면으로 이동하여 선택결과 받기
            val intent= if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU) Intent(MediaStore.ACTION_PICK_IMAGES).putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 10)
            else Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

            resultLauncher.launch(intent)

            //이 메소드의 2번째 파라미터 filePathCallback : 파일선택의 결과를 JS쪽으로 돌려주는 콜백객체
            mFilePathCallback= filePathCallback


            return true
            //return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
        }

        val resultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== RESULT_CANCELED){
                Toast.makeText(context, "파일 선택을 취소하셨습니다.", Toast.LENGTH_SHORT).show()
            }else{

                val imgs: MutableList<Uri> = mutableListOf()
                //1개를 선택하면 URI정보를 data로 받아옴
                //2개 이상을 선택하면 ClipData로 전달되어 옴
                if(it.data?.data != null ) imgs.add(it.data!!.data!!)
                else{
                    var cnt= it.data?.clipData?.itemCount!!
                    for( n in 0 until cnt) imgs.add( it.data!!.clipData!!.getItemAt(n)!!.uri)

                    //Toast.makeText(context, "선택한 파일 개수 ${imgs.size}", Toast.LENGTH_SHORT).show()

                    // 네이티브앱에서 대신 선택한 파일 uri 정보들을 웹뷰의 JS에 다시 전달..
                    val uris:Array<Uri> = imgs.toTypedArray()
                    mFilePathCallback!!.onReceiveValue(uris)
                }

            }

        }



        //웹뷰의 JS에서 alert()을 요청할 때 ..반응하기
        override fun onJsAlert(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            //Toast.makeText(context, "다이얼로그 호출", Toast.LENGTH_SHORT).show()

            val dialog= AlertDialog.Builder(context).setMessage(message).setPositiveButton("확인", { dialog, which -> result!!.confirm() }).create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()

            return true  //이 곳에서 이벤트 처리를 모두 소비했으니..더 이상 다른 작업하지 마라..
            //return super.onJsAlert(view, url, message, result)
        }

    }

}