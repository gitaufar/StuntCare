package com.example.stuntcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.raon.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login: AppCompatButton = findViewById(R.id.btnLogin)
        val register: AppCompatButton = findViewById(R.id.btnRegister)

        login.setOnClickListener(){

        }
    }
}