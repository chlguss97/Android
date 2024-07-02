package com.hyun.ex50datastoragesqlitedatabase

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    val inputLayoutName: TextInputLayout by lazy { findViewById(R.id.input_layout_name) }
    val inputLayoutAge: TextInputLayout by lazy { findViewById(R.id.input_layout_age) }
    val inputLayoutEmail: TextInputLayout by lazy { findViewById(R.id.input_layout_email) }

    // SQLiteDatabase 참조변수
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // test.db 라는 이름으로 데이터베이스 파일 열기 또는 생성
        // 이 메소드를 실행하면.. test.db를 제어할 수 있는 능력을 가진 객체를 리턴해 줌
        db= openOrCreateDatabase("test", MODE_PRIVATE, null)

        // 만들어진 DB파일에 표(table)를 생성하는 작업 수행 [ 테이블 명 : member ]
        // SQL 언어를 이용하여 원하는 명령을 Database에 실행
        db.execSQL("CREATE TABLE IF NOT EXISTS member(num INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), age INTEGER(3), email TEXT)")

        //버튼 클릭 리스너 처리
        findViewById<Button>(R.id.btn_insert).setOnClickListener { clickInsert() }
        findViewById<Button>(R.id.btn_update).setOnClickListener { clickUpdate() }
        findViewById<Button>(R.id.btn_delete).setOnClickListener { clickDelete() }
        findViewById<Button>(R.id.btn_select_all).setOnClickListener { clickSelectAll() }
        findViewById<Button>(R.id.btn_select_by_name).setOnClickListener { clickSelectByName() }
    }

    private fun clickInsert(){
        // 저장할 데이터들
        var name: String= inputLayoutName.editText!!.text.toString()
        var age: Int= inputLayoutAge.editText!!.text.toString().toInt()
        var email: String= inputLayoutEmail.editText!!.text.toString()

        // Database에 데이터를 삽입하는 쿼리문 언어 SQL 실행
        db.execSQL("INSERT INTO member(name, age, email) VALUES('$name','$age','$email')")

        inputLayoutName.editText!!.setText("")
        inputLayoutAge.editText!!.setText("")
        inputLayoutEmail.editText!!.setText("")
    }

    private fun clickUpdate(){

        //업데이트할 데이터(한줄 row)의 정보 가져오기
        var name: String = inputLayoutName.editText!!.text.toString()
        var age: Int = inputLayoutAge.editText!!.text.toString().toInt()
        var email: String = inputLayoutEmail.editText!!.text.toString()

        //업데이트 쿼리문 요청
        db.execSQL("UPDATE member SET age=?, email=? WHERE name=?", arrayOf(age,email,name))
    }

    private fun clickDelete(){
        var name: String = inputLayoutName.editText!!.text.toString()

        //삭제 쿼리문 요청
        db.execSQL("DELETE FROM member WHERE name=?", arrayOf(name))
    }

    private fun clickSelectAll(){

        // 모든 데이터(레코드:한줄 row)를 가져오기..단, name, age 칸만 가져오기
        // SELECT 쿼리문 실행 - 리턴값으로 검색결과표 객체를 줌
        val cursor: Cursor= db.rawQuery("SELECT name,age FROM member", null)

        // 리턴된 Cursor객체 : 검색 결과에 해당하는 데이터들로만 이루어진 새로운 표(table)를 가진 객체

        val buffer: StringBuffer = StringBuffer()
        cursor?.apply {
            // 첫 레코드로 커서 이동
            this.moveToFirst()

            // 총 레코드의 수만큼 반복하면서 한줄 레코드씩 데이터를 읽어오기
            for( i in 0 until this.count) { // cursor.count : 총 레코드 수...
                var name: String= this.getString(0) // 첫번째 칸 번호
                var age: Int= this.getInt(1) // 두번째 칸 번호

                buffer.append("$name : $age \n")

                // 다음 줄로 커서 이동
                this.moveToNext()
            }//for..

        }//apply...

        // 결과를 다이얼그에 보여주기
        AlertDialog.Builder(this).setMessage(buffer.toString()).create().show()

    }

    private fun clickSelectByName(){

        // 특정 이름에 해당하는 데이터를 검색
        var name: String= inputLayoutName.editText!!.text.toString()
        val cursor:Cursor? = db.rawQuery("SELECT * FROM member WHERE name=?", arrayOf(name))

        val buffer:StringBuffer = StringBuffer()
        cursor?.apply {
            moveToFirst() // 첫번째 레코드로 커서 이동

            for(i in 0 until count){ // count : 총 레코드 수
                var num:Int= getInt(0)
                var name:String= getString(1)
                var age:Int= getInt(2)
                var email:String= getString(3)

                buffer.append("$num - $name : $age ~ $email \n")
                moveToNext() //커서를 다음줄로...
            }//for
        }//apply...

        //결과를 다이얼로그로 보이기..
        AlertDialog.Builder(this).setMessage(buffer.toString()).create().show()

    }

}







