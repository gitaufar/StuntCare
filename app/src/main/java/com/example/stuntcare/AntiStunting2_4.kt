package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R

class AntiStunting2_4 : AppCompatActivity() {

    private lateinit var editTextWeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anti_stunting24)

//        val imageView11 = findViewById<ImageView>(R.id.imageView11)
        val nextButton = findViewById<Button>(R.id.button7)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight1)


        nextButton.setOnClickListener {
            val weight = editTextWeight.text.toString()
            val intent = Intent(this, AntiStunting2_5::class.java)
            startActivity(intent)
        }
    }
}
