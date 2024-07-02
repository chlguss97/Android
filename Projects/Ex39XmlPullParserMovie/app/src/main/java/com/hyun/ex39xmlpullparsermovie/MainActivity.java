package com.hyun.ex39xmlpullparsermovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieItem> itemList= new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter adapter;

    ProgressBar progressBar;

    //영화진흥위원회 api 에서 발급받은 접속 인증키...
    String apiKey="e7bf4a253ecfb76eb57ae172fabd5174";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar= findViewById(R.id.progress_bar);

        recyclerView= findViewById(R.id.recycler_view);
        adapter= new MovieAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(v -> loadData(1) );
        findViewById(R.id.btn2).setOnClickListener(v -> loadData(2) );

    }//onCreate method..

    void loadData(int kind){
        // 영화진흥위원회 OPEN API 서버에 접속하여 일일박스오피스 XML정보를 가져와서 분석하여 보여주기
        // 인터넷을 과금이 있을 수 있기에..허가(permission)를 받아야 함. AndroidManifest.xml 에서 작업

        // 네트워크 작업은 오래 걸리는 작업 .. 별도의 Thread를 이용해야 함.
        new Thread() {
            @Override
            public void run() {

                // 기존 영화리스트의 목록을 모두 삭제..
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        itemList.clear();
                        adapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.VISIBLE);
                    }
                });



                //영화진흥위원회 api 서버의 URL 정보 - 일일박스오피스 + 요청파라미터
                String address="";

                if(kind==1){ //일별 박스오피스

                    // 자바의 클래스 중에 현재 날짜값을 가지고 있는 클래스가 존재함
                    Calendar calendar= Calendar.getInstance();
                    calendar.add(Calendar.DATE,-1); // 하루전
                    // 날짜를 특정 포멧의 문자열로 만들어주는 객체가 존재함
                    SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                    String date= sdf.format(calendar.getTime() );

                    address="https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml"
                            +"?key=" + apiKey
                            +"&targetDt="+date
                            +"&itemPerPage=10";

                }else if(kind==2){ //주간 박스오피스

                    // 자바의 클래스 중에 현재 날짜값을 가지고 있는 클래스가 존재함
                    Calendar calendar= Calendar.getInstance();
                    calendar.add(Calendar.DATE,-7); // 일주일 전
                    //calendar.add(Calendar.YEAR, -1); //일년전
                    //calendar.add(Calendar.MONTH, -1); //한달전


                    // 날짜를 특정 포멧의 문자열로 만들어주는 객체가 존재함
                    SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                    String date= sdf.format(calendar.getTime());



                    address="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.xml"
                            +"?key=" + apiKey
                            +"&targetDt="+date
                            +"&itemPerPage=10"
                            +"&weekGb=0";
                }

                Log.d("aaa", address);


                //위 주소까지 연결되는 무지개로드(Stream)를 열어주는 해임달(URL) 객체 생성
                try {
                    URL url= new URL(address);
                    InputStream is= url.openStream(); //바이트 스트림
                    InputStreamReader isr= new InputStreamReader(is);//바이트-->문자

                    //스트림을 통해 xml 파일을 읽어와서 분석해주는 분석가 준비
                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    XmlPullParser xpp= factory.newPullParser();
                    xpp.setInput(isr);

                    //분석가를 이용하여 분석작업 시작!!
                    int eventType= xpp.getEventType();

                    MovieItem item= null; //영화1개 정보객체의 참조변수

                    while ( eventType != XmlPullParser.END_DOCUMENT ){

                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                //직원 스레드 안에서는 UI 작업이 불가능.. 그래서 UI Thread에서 작업해야 함.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "파싱 시작!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                String tagName= xpp.getName();
                                if(tagName.equals("dailyBoxOffice")){
                                    item= new MovieItem(); //빈 박스 만들기

                                }else if(tagName.equals("weeklyBoxOffice")){
                                    item= new MovieItem(); //빈 박스 만들기

                                }else if(tagName.equals("rank")){
                                    xpp.next();
                                    item.rank= xpp.getText();

                                }else if(tagName.equals("movieNm")){
                                    xpp.next();
                                    item.movieNm= xpp.getText();

                                }else if(tagName.equals("openDt")){
                                    xpp.next();
                                    item.openDt= xpp.getText();

                                }else if(tagName.equals("audiAcc")){
                                    xpp.next();
                                    item.audiAcc= xpp.getText();

                                }

                                break;

                            case XmlPullParser.END_TAG:
                                String tagName2= xpp.getName();
                                if(tagName2.equals("dailyBoxOffice") || tagName2.equals("weeklyBoxOffice")){
                                    itemList.add(item);
                                }

                                break;
                        }


                        eventType= xpp.next();
                    }//while

                    // 반복문이 끝났다면..분석작업이 완료되었다는 것이고..
                    // 그 과정에서 itemList에 영화정보들이 주렁주렁 추가되어 있었을 것임.
                    // 잘 추가되었는지 확인하기 위해..
                    Log.d("aaa", "리스트의 개수 : " + itemList.size());

                    // 아답터가 보여줄 대량의 데이터가 변경되었다고 알려주면..자동으로 화면을 갱신하여 대량의 데이터를 보여줌
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();

                            progressBar.setVisibility(View.GONE);
                        }
                    });


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



}//MainActivity class..