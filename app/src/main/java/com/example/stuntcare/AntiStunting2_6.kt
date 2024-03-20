package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AntiStunting2_6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anti_stunting26)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = Firebase.database
        val ref = db.getReference(currentUser?.uid!!)

        val editTextAlergy = findViewById<EditText>(R.id.editTextText11)
        val nextButton = findViewById<Button>(R.id.buttonNext)

        nextButton.setOnClickListener {
            val alergy = editTextAlergy.text.toString()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("alergy", alergy)
            startActivity(intent)
        }

        val btnListAlergy = findViewById<Button>(R.id.btnAlergy)
        btnListAlergy.setOnClickListener {
            showAlergyAlertDialog()
        }
    }

    private fun showAlergyAlertDialog() {
        val alergy = arrayOf("Egg", "Seafood", "Lactose intolerant", "Peanut")
        val checkedItems = booleanArrayOf(false, false, false, false)

        val editTextAlergy = findViewById<EditText>(R.id.editTextText11)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        buttonNext.setOnClickListener {

            val historyOfAlergy = findViewById<EditText>(R.id.editTextText11).text.toString()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("historyOfAlergy", historyOfAlergy)
            startActivity(intent)
        }
        val buttonSkip = findViewById<Button>(R.id.buttonSkip)
        buttonSkip.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val imageViewBack = findViewById<ImageView>(R.id.imageView18)
        imageViewBack.setOnClickListener {
            onBackPressed()

        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("List of Alergy")
        builder.setMultiChoiceItems(alergy, checkedItems) { dialog, which, isChecked ->

        }
        builder.setPositiveButton("OK") { dialog, which ->
            val selectedAlergy = StringBuilder()
            for (i in checkedItems.indices) {
                if (checkedItems[i]) {
                    selectedAlergy.append(alergy[i]).append("\n")
                }
            }

            selectedAlergy.append(editTextAlergy.text.toString())

            val historyOfAlergy = selectedAlergy.toString()
            val auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            val db = Firebase.database
            val ref = db.getReference(currentUser?.uid!!)
            ref.child("Alergy").setValue(historyOfAlergy)

            editTextAlergy.setText(historyOfAlergy)
            showToast("Selected Alergy:\n$historyOfAlergy")

        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}