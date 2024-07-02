package com.hyun.ex37threadnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.iv);

        findViewById(R.id.btn).setOnClickListener(v -> {
            //네트워크 작업은 과금이 있을 수 있는 기능임. 인터넷 사용에 대한 권한을 먼저 획득해야 함. 권한 작업은 AndroidManifest.xml.에서 작업함.
            //네트워크 작업은 오래 걸리는 작업으로 판단함. 그래서 반드시 별도 Thread를 사용해야 함.
            //MyThread t= new MyThread();
            //t.start();


            //이미지 로드 라이브러리를 이용하여 서버의 이미지를 불러와서 보여주기
            //Glide.with(this).load("https://cdn.pixabay.com/photo/2018/02/26/21/04/owl-3184032_1280.jpg").into(iv);
            //Glide.with(this).load(R.drawable.newyork).into(iv);

            //gif 이미지
            //iv.setImageResource(R.drawable.winter);
            Glide.with(this).load(R.drawable.winter).into(iv);



        });
    }//onCreate method

    //inner class
    class MyThread extends Thread{
        @Override
        public void run() {
            // 읽어올 이미지의 인터넷경로 주소 URL
            String imgAddreess="https://cdn.pixabay.com/photo/2020/02/28/09/41/snow-4886995_1280.jpg";


            try {
                //위 주소의 서버까지 무지개로드(Stream)을 열어주는 해임달(URL) 객체 생성
                URL url= new URL(imgAddreess);

                //무지개로드 열기
                InputStream is= url.openStream();

                //스트림을 통해 이미지를 읽어와서 이미지 뷰에 설정
                //이미지파일을 자바에서 읽어오면 .. byte[]로 읽어옴. 하지만 byte[]은 이미지뷰에 보여질 수없는 데이터 형식
                //byte[]을 스트림을 통해 읽어와서 이미지뷰에 넣을 수 있도록 자바 객체화 시켜야함.
                //그 객체의 클래스명이 Bitmap 클래스임
                Bitmap bm= BitmapFactory.decodeStream(is);
                //그림을 가진 객체 Bitmap을 이미지뷰에 설정하여 보여주기
                //iv.setImageBitmap(bm);
                //별도 Thread는  UI작업이 불가능
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(bm);
                    }
                });

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}//MainActivity class