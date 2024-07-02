package com.hyun.floodedcar;

import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Page1Fragment extends Fragment {

    TextView tv;
    TextInputEditText tie;
    String key= "rLyO0JLoIujYpTf6N9%2F1lio3FyURE%2BTS3PQXfjXnzXVf0oBU8mO9gqmXnnU1gn2TOmUexojv%2Fjpn79ONN0ZTaA%3D%3D";
    String carnum="";
    ResultItem item = null;








    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.fragment_page1, container, false);
    return view;



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv= view.findViewById(R.id.tv);
        tie= view.findViewById(R.id.tie);

       view.findViewById(R.id.btn).setOnClickListener(v -> {
           carnum= tie.getText().toString().replace(" ","");
           if(carnum.equals("")){
               Toast.makeText(getContext(), "차량번호를 입력하시오", Toast.LENGTH_SHORT).show();

           }else{
                   loadData(1);}



       });






    }


    void loadData (int kind) {

        new Thread(){
            @Override
            public void run() {
                String address="";
                address="http://apis.data.go.kr/1160100/service/GetASLundService/getAutomobileLundinfo"
                        +"?&acdnKindNm="
                        +"&nowVhclNo="+carnum
                        +"&pageNo=1&numOfRows=1"
                        +"&serviceKey="+key;

                Log.d("aa","dddd");

                try {
                    URL url= new URL(address);
                    InputStream is= url.openStream();
                    InputStreamReader isr= new InputStreamReader(is);

                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    XmlPullParser xpp= factory.newPullParser();
                    xpp.setInput(isr);


                    int eventType= xpp.getEventType();




                    while ( eventType != XmlPullParser.END_DOCUMENT){

                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(), "파싱시작", Toast.LENGTH_SHORT).show();

                                    }
                                });
                                break;



                            case XmlPullParser.START_TAG:
                                String tagName = xpp.getName();
                                if(tagName.equals("resultCode")){
                                    item=new ResultItem();

                                }else if(tagName.equals("resultMsg")){
                                    xpp.next();
                                    item.resultMsg=xpp.getText();

                                }else if(tagName.equals("numOfRows")){
                                    xpp.next();
                                    item.numOfRows=xpp.getText();

                                }else if(tagName.equals("pageNo")){
                                    xpp.next();
                                    item.pageNo=xpp.getText();

                                }else if(tagName.equals("totalCount")){
                                    xpp.next();
                                    item.totalCount=xpp.getText();

                                }else if(tagName.equals("dtaWrtDt")){
                                    xpp.next();
                                    item.dtaWrtDt=xpp.getText();

                                }else if(tagName.equals("acdnOccrDtm")){
                                    xpp.next();
                                    item.acdnOccrDtm=xpp.getText();

                                }else if(tagName.equals("acdnKindNm")){
                                    xpp.next();
                                    item.acdnKindNm=xpp.getText();

                                }else if(tagName.equals("nowVhclNo")){
                                    xpp.next();
                                    item.nowVhclNo=xpp.getText();
                                }


                                break;


                            case XmlPullParser.END_TAG:
                            { String tagname2 = xpp.getName();
                                if(tagname2.equals("resultCode")){

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(item.nowVhclNo!=null) {
                                                item.resultMsg = "!!주의!! 침수 기록이 존재합니다.";
                                                tv.setText("결과\n - " + item.resultMsg
                                                        + "\n\n" + "작성일자\n - " + item.dtaWrtDt
                                                        + "\n\n" + "현재 차량번호\n - " + item.nowVhclNo
                                                        + "\n\n" + "사고 종류명\n - " + item.acdnKindNm
                                                        + "\n\n" + "사고발생일시\n - " + item.acdnOccrDtm
                                                );
                                            }else tv.setText("이 차량은 침수차량이 아닙니다.");
                                        }
                                    });

                                }
                            }



                            break;
                        }

                        eventType= xpp.next();
                        //tv.setText(item.pageNo+","+item.acdnKindNm+","+item.acdnOccrDtm+",");


                    }




                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();






    }


}
