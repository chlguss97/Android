package com.hyun.ex38xmlresourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(v -> {
            // xml 데이터를 분석하여 TextView에 보여주는 작업을 수행하는 기능 메소드를 호출
            loadData();
        });
    }//onCreate method

    // 분석작업을 수행하는 코드가 작성되어 있는 영역 메소드
    public void loadData(){

        Resources res= getResources();
        XmlResourceParser xrp= res.getXml(R.xml.movies);

        try{
            xrp.next();
            int eventType= xrp.getEventType();

            StringBuffer buffer= new StringBuffer();

            while (eventType != XmlResourceParser.END_DOCUMENT){
                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("xml 파싱을 시작합니다.\n\n");
                        break;
                    case XmlResourceParser.START_TAG:
                        String tagName= xrp.getName();
                        if(tagName.equals("item")){

                        }else if(tagName.equals("no")){
                            buffer.append("번호: ");

                        }else if(tagName.equals("title")){
                            buffer.append("제목: ");

                        }else if(tagName.equals("genre")){
                            buffer.append("장르: ");

                        }

                        break;
                    case XmlResourceParser.END_TAG:
                        String tagName2= xrp.getName();
                        if(tagName2.equals("item")){
                            buffer.append("---------------------\n\n");
                        }

                        break;
                    case XmlResourceParser.TEXT:
                        String text= xrp.getText();
                        buffer.append(text +"\n");
                        break;
                }

                xrp.next();
                eventType= xrp.getEventType();
            }//while

            buffer.append("분석작업을 모두 완료했습니다.");

            tv.setText(buffer.toString());
        }catch (Exception e){
            tv.setText("파싱 중에 오류가 발생했습니다.");
        }


    }

}//MainActivity class