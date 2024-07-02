package com.hyun.ex28bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    FragmentManager fragmentManager;
    Fragment[] fragments= new Fragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프레그먼트 관리자 소환...
        fragmentManager= getSupportFragmentManager();

        fragments[0]= new HomeFragment();
        fragments[1]= new SearchFragment();
        fragments[2]= new LocationFragment();

        //처음 보여질 프레그먼트를 일단 붙여놓고 시작
        fragmentManager.beginTransaction().add(R.id.container, fragments[0]).commit();

        bnv= findViewById(R.id.bnv);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.bnv_home){
                    //Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();

                    //HomeFragment 객체를 화면에 붙이기..
                    fragmentManager.beginTransaction().replace(R.id.container, fragments[0]).commit();

                }else if(item.getItemId()==R.id.bnv_search){
                    //Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();

                    fragmentManager.beginTransaction().replace(R.id.container, fragments[1]).commit();

                }else if(item.getItemId()==R.id.bnv_location){
                    //Toast.makeText(MainActivity.this, "location", Toast.LENGTH_SHORT).show();

                    fragmentManager.beginTransaction().replace(R.id.container, fragments[2]).commit();
                }

                //리턴값이 true가 아니면 선택에 따른 UI변경작업이 보이지 않음.
                return true;
            }
        });

        //처음 선택될 BNV의 아이템 지정
        bnv.setSelectedItemId(R.id.bnv_search);

    }
}