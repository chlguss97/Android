package com.mrhi2024.exdatabinding

import androidx.databinding.ObservableField

class User {
    var name: ObservableField<String> = ObservableField<String>("sam")
    var age:Int= 0

    fun changeName(){
        name.set("robin")
    }
}