package com.hyun.ex28bottomnavigationview;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    TextInputLayout inputLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputLayout= view.findViewById(R.id.input_layout);
        view.findViewById(R.id.btn).setOnClickListener(v -> {
            String msg= inputLayout.getEditText().getText().toString();
            new AlertDialog.Builder(getActivity()).setMessage(msg).create().show();

       //소프트 키보드 감추기..
            InputMethodManager imm= (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(inputLayout.getWindowToken(), 0);


        });
    }
}
