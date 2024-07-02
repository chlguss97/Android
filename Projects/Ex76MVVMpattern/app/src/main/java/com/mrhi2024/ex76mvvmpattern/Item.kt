package com.mrhi2024.ex76mvvmpattern

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class Item {
    var message: ObservableField<String> = ObservableField("Hello")
    var age:ObservableInt = ObservableInt(0)

    fun changeText(){
        message.set("Nice to meet you")
    }
}