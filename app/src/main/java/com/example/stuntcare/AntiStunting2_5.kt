package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AntiStunting2_5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anti_stunting25)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = Firebase.database
        val ref = db.getReference(currentUser?.uid!!)

        val editTextIllness = findViewById<EditText>(R.id.editTextText10)
        val nextButton = findViewById<Button>(R.id.buttonNext)

        nextButton.setOnClickListener {
            val illness = editTextIllness.text.toString()

            val intent = Intent(this, AntiStunting2_6::class.java)
            intent.putExtra("illness", illness)
            startActivity(intent)
        }

        val btnListIllness = findViewById<Button>(R.id.btnListIllness)
        btnListIllness.setOnClickListener {
            showIllnessAlertDialog()
        }
    }

    private fun showIllnessAlertDialog() {
        val illnesses = arrayOf("Anemia", "Asma", "Diabetes", "Kanker")
        val checkedItems = booleanArrayOf(false, false, false, false)

        val editTextIllness = findViewById<EditText>(R.id.editTextText10)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        buttonNext.setOnClickListener {
            val historyOfIllness = findViewById<EditText>(R.id.editTextText10).text.toString()

            val intent = Intent(this, AntiStunting2_6::class.java)
            intent.putExtra("historyOfIllness", historyOfIllness)
            startActivity(intent)
        }

        val buttonSkip = findViewById<Button>(R.id.buttonSkip)
        buttonSkip.setOnClickListener {
            val intent = Intent(this, AntiStunting2_6::class.java)
            startActivity(intent)
        }

        val imageViewBack = findViewById<ImageView>(R.id.imageView17)
        imageViewBack.setOnClickListener {
            onBackPressed()
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("List of Illness")
        builder.setMultiChoiceItems(illnesses, checkedItems) { dialog, which, isChecked ->

        }
        builder.setPositiveButton("OK") { dialog, which ->
            val selectedIllnesses = StringBuilder()
            for (i in checkedItems.indices) {
                if (checkedItems[i]) {
                    selectedIllnesses.append(illnesses[i]).append("\n")
                }
            }

            val historyOfIllness = selectedIllnesses.toString()
            val auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            val db = Firebase.database
            val ref = db.getReference(currentUser?.uid!!)
            ref.child("History Of Illness").setValue(historyOfIllness)

            editTextIllness.setText(historyOfIllness)
            showToast("Selected Illnesses:\n$historyOfIllness")

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