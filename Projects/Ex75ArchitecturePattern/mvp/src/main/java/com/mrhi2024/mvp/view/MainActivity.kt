package com.mrhi2024.mvp.view

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mrhi2024.mvp.databinding.ActivityMainBinding
import com.mrhi2024.mvp.model.Item
import com.mrhi2024.mvp.presenter.MainContract
import com.mrhi2024.mvp.presenter.MainPresenter


class MainActivity : AppCompatActivity(), MainContract.View {

    // MVP 패턴
    // 1) Model : 데이터를 제어하는 비지니스 로직 관련 코드...
    // 2) View : 화면 구현에 관련된 코드
    // 3) Presenter : 뷰와 모델 사이에서 연결하는 역할. interface로 각 역할을 정해놓기에 특정 객체를 만들어 참조하여 결합되는 것을 방지함.

    // 뷰의 역할...[화면구현과 클릭이벤트 처리]
    lateinit var binding: ActivityMainBinding

    // # presenter 객체 참조변수
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // # presenter 객체 생성 및 초기화
        presenter= MainPresenter()
        presenter.initial(this)

        //# view역할
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //# view역할2
        binding.btnSave.setOnClickListener { presenter.clickedSave(binding.etName.text.toString(), binding.etEmail.text.toString()) }
        binding.btnLoad.setOnClickListener { presenter.clickedLoad() }

    }

    /////////////////////////////////////////////////////
    override fun showData(item: Item) {
        binding.tv.text= "${item.name} - ${item.email}"
    }

    override fun getContext(): Context {
        return this
    }
    ////////////////////////////////////////////////////////

}