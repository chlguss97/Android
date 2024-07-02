package com.mrhi2021.tp05widgetex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Toast, Dialog 수업 후 자바코드 과제 수행!

    ImageView dialogIv;
    ImageView dialogBtn;

    int n=0;

    int[] imgs= new int[]{R.drawable.newyork, R.drawable.paris, R.drawable.sydney};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickOverflow(View view) {
        Toast.makeText(this, "overflow", Toast.LENGTH_SHORT).show();
    }

    public void clickHeart(View view) {
        Toast.makeText(this, "Heart", Toast.LENGTH_SHORT).show();
    }

    public void clickComment(View view) {
        Toast.makeText(this, "Comment", Toast.LENGTH_SHORT).show();
    }

    public void clickSend(View view) {
        Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
    }

    public void clickBookmark(View view) {
        Toast.makeText(this, "Bookmark", Toast.LENGTH_SHORT).show();
    }

    public void clickImage(View view) {
        LayoutInflater inflater= getLayoutInflater();
        View layout= inflater.inflate(R.layout.item_cardview, null);

        new AlertDialog.Builder(this).setView(layout).create().show();

        dialogIv= layout.findViewById(R.id.item_iv);
//        dialogIv.setImageResource(R.drawable.moana);
        dialogIv.setImageResource(R.drawable.newyork);
        dialogIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n++;
//                if(n>=5) n=0;
//                dialogIv.setImageResource(R.drawable.moana+n);
                if(n>=imgs.length) n=0;
                dialogIv.setImageResource(imgs[n]);
            }
        });

        dialogBtn= layout.findViewById(R.id.item_btn);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n++;
//                if(n>=5) n=0;
//                dialogIv.setImageResource(R.drawable.moana+n);
                if(n>=imgs.length) n=0;
                dialogIv.setImageResource(imgs[n]);
            }
        });

//        minSDK API 21(LOLLIPOP) 이상일때는 LayoutInflater 가 반드시 필요하지 않음. [ dialog에게 직접 find View 하도록 요청할 수 있음. 단, show()한 후에 가능함 ]
//        AlertDialog dialog= new AlertDialog.Builder(this).setView(R.layout.item_cardview).create();
//        dialog.show();
//
//        dialogIv= dialog.findViewById(R.id.item_iv);
//        dialogIv.setImageResource(R.drawable.moana);
//        dialogIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                n++;
//                if(n>=5) n=0;
//                dialogIv.setImageResource(R.drawable.moana+n);
//            }
//        });
//
//        dialogBtn= dialog.findViewById(R.id.item_btn);
//        dialogBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                n++;
//                if(n>=5) n=0;
//                dialogIv.setImageResource(R.drawable.moana+n);
//            }
//        });


    }

}