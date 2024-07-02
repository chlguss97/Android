package com.snacksoft.wordgamev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.snacksoft.wordgamev2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var questions:MutableList<Question> = mutableListOf()
    var num:Int= 0
    var score:Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //퀴즈 데이터 가져오기.
        loadQuestion()

        startGame()

        binding.btnNext.setOnClickListener { clickNext() }

        binding.btn01.setOnClickListener { clickBtn(it) }
        binding.btn02.setOnClickListener { clickBtn(it) }
        binding.btn03.setOnClickListener { clickBtn(it) }
        binding.btn04.setOnClickListener { clickBtn(it) }

        binding.btnRestart.setOnClickListener { startGame() }
    }

    private fun clickBtn(view:View){
        if( (view as Button).text == questions[num].answer ){
            score += 10
            binding.tvResult.text="GOOD"
        }else{
            binding.tvResult.text= "BAD"
        }

        binding.btn01.isEnabled= false
        binding.btn02.isEnabled= false
        binding.btn03.isEnabled= false
        binding.btn04.isEnabled= false

        binding.btnNext.visibility= View.VISIBLE
    }

    private fun clickNext(){
        num++
        if(num>questions.size-1) {
            gameOver()
            return
        }
        showQuestionUI()
    }

    fun gameOver(){
        //Toast.makeText(this, "gameover", Toast.LENGTH_SHORT).show()
        binding.layoutGameOver.visibility= View.VISIBLE

        binding.tvScore.text= "$score"
    }

    private fun startGame(){

        questions.shuffle() //퀴즈 순서 뒤섞기
        num=0
        score=0

        //퀴즈 화면 UI 설정
        showQuestionUI()

        binding.layoutGameOver.visibility= View.GONE
    }

    private fun showQuestionUI(){
        val question:Question = questions[num]

        binding.tvQuestion.text= question.question
        Glide.with(this).load(question.img).into(binding.ivQuestion)

        question.ex.shuffle()

        binding.btn01.text = question.ex[0]
        binding.btn02.text = question.ex[1]
        binding.btn03.text = question.ex[2]
        binding.btn04.text = question.ex[3]

        binding.tvNum.text= "${num+1}/10"
        binding.tvResult.text= ""

        binding.btn01.isEnabled= true
        binding.btn02.isEnabled= true
        binding.btn03.isEnabled= true
        binding.btn04.isEnabled= true

        binding.btnNext.visibility= View.GONE
    }

    private fun loadQuestion(){
        questions.add( Question("Calendar", R.drawable.calendar, "달력", arrayOf("달력","집","사탕","인형")) )
        questions.add( Question("Car", R.drawable.car, "자동차", arrayOf("번개","기차","자동차","자전거")) )
        questions.add( Question("Money", R.drawable.money, "돈", arrayOf("음악","황금","돈","종이")) )
        questions.add( Question("Pencil", R.drawable.pencil, "연필", arrayOf("지우개","종이","연필","볼펜")) )
        questions.add( Question("Rainbow", R.drawable.rainbow, "무지개", arrayOf("무지개","구름","바람","태양")) )
        questions.add( Question("Recycle", R.drawable.recycle, "재활용", arrayOf("재활용","자건거","회전","편지")) )
        questions.add( Question("Scissors", R.drawable.recycle, "가위", arrayOf("가위","풀","지우개","연필")) )
        questions.add( Question("Train", R.drawable.train, "기차", arrayOf("기차","자동차","비행기","배")) )
        questions.add( Question("Umbrella", R.drawable.umbrella, "우산", arrayOf("우산","장화","비","무지개")) )
        questions.add( Question("Weather", R.drawable.weather, "날씨", arrayOf("날씨","물","창문","바람")) )
    }
}