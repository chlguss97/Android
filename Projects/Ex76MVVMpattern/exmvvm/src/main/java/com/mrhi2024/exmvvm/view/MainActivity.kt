package com.mrhi2024.exmvvm.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mrhi2024.exmvvm.R
import com.mrhi2024.exmvvm.databinding.ActivityMainBinding
import com.mrhi2024.exmvvm.viewmodel.ItemViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //# view역할 : 화면을 만드는 작업 코드
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //# xml의 <data>영역에 만든 변수(vm)에 값을 대입
        binding.vm= ItemViewModel(this)
    }
}