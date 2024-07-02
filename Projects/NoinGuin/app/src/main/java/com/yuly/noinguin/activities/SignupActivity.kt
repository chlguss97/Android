package com.yuly.noinguin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.size
import com.yuly.noinguin.R
import com.yuly.noinguin.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    val items = arrayOf("50대","60대","70대","80대","90대")











    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTakePhoto.setOnClickListener { takePhoto() }
        binding.btnPickPhoto.setOnClickListener { pickPhoto() }
        binding.btnIdConfirm.setOnClickListener { idConfirm() }
        binding.btnOk.setOnClickListener { btnOk() }

        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        binding.spinner.adapter= myAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0-> Toast.makeText(this@SignupActivity, "dd", Toast.LENGTH_SHORT).show()
                    1-> Toast.makeText(this@SignupActivity, "dd", Toast.LENGTH_SHORT).show()
                    2-> Toast.makeText(this@SignupActivity, "dd", Toast.LENGTH_SHORT).show()
                    3-> Toast.makeText(this@SignupActivity, "dd", Toast.LENGTH_SHORT).show()
                    4-> Toast.makeText(this@SignupActivity, "dd", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@SignupActivity, "낫클릭", Toast.LENGTH_SHORT).show()
            }

        }

    }



    private fun takePhoto(){
        //사진찍기
    }

    private fun pickPhoto(){
        //사진가져오기

    }

    private fun idConfirm(){
        //서버에 아이디중복확인

    }

    private fun btnOk(){
        //서버에 저장 . 액티비티이동

    }
}