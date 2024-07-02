package com.hyun.ex42broadcastreceiverbooting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 알림 사용에 대한 동적 퍼미션 요청
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){

            String[] permissions= new String[] {Manifest.permission.POST_NOTIFICATIONS};
            int permissionResult= checkSelfPermission(permissions[0]);
            if(permissionResult== PackageManager.PERMISSION_DENIED) requestPermissions(permissions,10);

        }

    }//onCreate method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==10){
            if (grantResults[0] ==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "알림을 사용할 수 있습니다", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "알림 사용을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

    }
}
