package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AntiStunting2_1 : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private lateinit var imageViewCalendar: ImageView
    private lateinit var calendarView: CalendarView
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anti_stunting21)

        editTextDate = findViewById(R.id.editTextText5)
        imageViewCalendar = findViewById(R.id.imageView8)
        calendarView = findViewById(R.id.calendarView2)
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = Firebase.database
        val ref = db.getReference(currentUser?.uid!!)

        calendarView.visibility = View.GONE

        editTextDate.setOnClickListener {
            showCalendar()
        }

        imageViewCalendar.setOnClickListener {
            showCalendar()
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            editTextDate.setText(selectedDate)
            hideCalendar()
        }

        buttonNext = findViewById(R.id.button5)

        buttonNext.setOnClickListener {
            val lastPeriod = editTextDate.text.toString()
            if(lastPeriod != "") {
                ref.child("lastPeriod").setValue(lastPeriod)
                navigateToNextPage()
            }
        }
    }

    private fun showCalendar() {
        calendarView.visibility = View.VISIBLE
    }

    private fun hideCalendar() {
        calendarView.visibility = View.GONE
    }

    private fun navigateToNextPage() {
        val intent = Intent(this, AntiStunting2_3::class.java)
        startActivity(intent)
    }
}