package com.mrhi2024.poketmonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener {
            thread {

                val url= URL("https://pokeapi.co/api/v2/pokemon/1")
                val inputStream= url.openStream()
                val inputStreamReader= InputStreamReader(inputStream)
                val reader= BufferedReader(inputStreamReader)

                val builder= StringBuilder()
                while (true){
                    val line= reader.readLine() ?: break
                    builder.append(line+"\n")
                    Log.d("aa", line)
                }

                //runOnUiThread { AlertDialog.Builder(this).setMessage(builder.toString()).create().show() }

                val s:String= builder.toString()

                val gson= Gson()
                val poketmonItem:PoketmonItem= gson.fromJson(s, PoketmonItem::class.java)

                runOnUiThread {  AlertDialog.Builder(this).setMessage("${poketmonItem.id} : ${poketmonItem.name}").create().show() }

//                val json:JSONObject= JSONObject(s)
//
//                val id:Int= json.getInt("id")
//                val name:String= json.getString("name")
//
//                runOnUiThread {  AlertDialog.Builder(this).setMessage("$id : $name").create().show() }

            }
        }
    }
}