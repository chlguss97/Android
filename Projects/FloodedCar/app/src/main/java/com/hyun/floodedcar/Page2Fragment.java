package com.hyun.floodedcar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.MalformedURLException;
import java.net.URL;

public class Page2Fragment extends Fragment {


    String address;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page2, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        address="https://www.car365.go.kr/web/contents/totalhistory.do";

        view.findViewById(R.id.btn).setOnClickListener(v -> {
            Intent intent= new Intent(Intent.ACTION_VIEW);

            Uri uri= Uri.parse(address);
            intent.setData(uri);
            startActivity(intent);

        });

    }
}
