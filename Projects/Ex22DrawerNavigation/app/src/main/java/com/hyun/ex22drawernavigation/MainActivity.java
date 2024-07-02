package com.hyun.ex22drawernavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle; //서랍 열고 닫는 기능 버튼 참조변수
    MaterialToolbar toolbar;

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= findViewById(R.id.toolbar);
        drawerLayout= findViewById(R.id.drawer_layout);

        navigationView= findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.menu_aa) Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
                else if(item.getItemId()==R.id.menu_bb) Toast.makeText(MainActivity.this,"bb",Toast.LENGTH_SHORT).show();
                else if(item.getItemId()==R.id.menu_logout) Toast.makeText(MainActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();

                //NavigationView 서랍을 닫기.
                drawerLayout.closeDrawer(navigationView);


                return false;
            }
        });


        //서랍 열고닫는 객체 생성
        drawerToggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);


        //NavigationView 안에 headerLayout 에 배치된 자식뷰들 찾아오기
        iv= navigationView.getHeaderView(0).findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "iv",Toast.LENGTH_SHORT).show();
            }
        });

    }//onCreate method...
}//MainActivity class..


