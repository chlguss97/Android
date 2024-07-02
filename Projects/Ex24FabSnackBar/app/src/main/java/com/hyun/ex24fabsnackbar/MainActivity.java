package com.hyun.ex24fabsnackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ExtendedFloatingActionButton fab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab= findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast 개량형...
                Snackbar snackbar= Snackbar.make(fab, "This is snackbar", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"clicked ok", Toast.LENGTH_SHORT).show();
                    }
                });
                snackbar.show();

            }
        });

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view= findViewById(R.id.layout);
                Snackbar.make(view, "Hello", Snackbar.LENGTH_LONG).show();
            }
        });


        fab2=findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fab2.isExtended()) fab2.shrink();
                else fab2.extend();
            }
        });



    }
}









