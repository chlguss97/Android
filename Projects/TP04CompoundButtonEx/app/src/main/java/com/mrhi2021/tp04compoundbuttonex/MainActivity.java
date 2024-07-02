package com.mrhi2021.tp04compoundbuttonex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edit_name;
    RadioGroup rg_gender;
    RadioGroup rg_city;
    EditText edit_p1, edit_p2, edit_p3;
    CheckBox chb_email, chb_phone, chb_visit, chb_sms;

    TextView text_list;

    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_name= findViewById(R.id.edit_name);
        rg_gender= findViewById(R.id.rg_gender);
        rg_city= findViewById(R.id.rg_city);
        edit_p1= findViewById(R.id.edit_phone1);
        edit_p2= findViewById(R.id.edit_phone2);
        edit_p3= findViewById(R.id.edit_phone3);
        chb_email= findViewById(R.id.chb_email);
        chb_phone= findViewById(R.id.chb_phone);
        chb_visit= findViewById(R.id.chb_visit);
        chb_sms= findViewById(R.id.chb_sms);

        text_list= (TextView)findViewById(R.id.text_list);

        edit_p1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==3) edit_p2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edit_p2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==4) edit_p3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void mOnClick(View v){
        switch( v.getId() ){
            case R.id.btn_regist:

                String s="";

                s += edit_name.getText().toString() + "  ";

                int rbId;

                rbId= rg_gender.getCheckedRadioButtonId();
                if( rbId== R.id.rb_male) s += "MALE" + "  ";
                else s+= "FEMALE" + "  ";

                rbId= rg_city.getCheckedRadioButtonId();
                if( rbId== R.id.rb_seoul) s += "SEOUL" + "  ";
                else if( rbId== R.id.rb_busan) s += "BUSAN" + " ";
                else s += "ETC" + "  ";

                s += edit_p1.getText().toString()+"-";
                s += edit_p2.getText().toString()+"-";
                s += edit_p3.getText().toString()+"  ";

                if( chb_email.isChecked() ) s+= "E-MAIL,";
                if( chb_phone.isChecked() ) s+= "PHONE,";
                if( chb_visit.isChecked() ) s+= "VISIT,";
                if( chb_sms.isChecked() )   s+= "SMS";

                str += s+"\n";

                text_list.setText(str);

                break;
        }
    }

}