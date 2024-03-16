package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.R

class AntiStunting2_5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anti_stunting25)

        val editTextIllness = findViewById<EditText>(R.id.editTextText10)
        val nextButton = findViewById<Button>(R.id.button11)

        nextButton.setOnClickListener {
            val illness = editTextIllness.text.toString()

            val intent = Intent(this, AntiStunting2_4::class.java)
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

        val buttonNext = findViewById<Button>(R.id.button11)
        buttonNext.setOnClickListener {

            val historyOfIllness = findViewById<EditText>(R.id.editTextText10).text.toString()
            // Start AntiStunting2 activity
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("historyOfIllness", historyOfIllness)
            startActivity(intent)
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("List of Illness")
        builder.setMultiChoiceItems(illnesses, checkedItems) { dialog, which, isChecked ->
            // Do something when a checkbox is clicked
        }
        builder.setPositiveButton("OK") { dialog, which ->
            val selectedIllnesses = StringBuilder()
            for (i in checkedItems.indices) {
                if (checkedItems[i]) {
                    selectedIllnesses.append(illnesses[i]).append("\n")
                }
            }
            // Do something with the selected illnesses
            // For example, display them in a toast message
            selectedIllnesses.toString().let {
                showToast("Selected Illnesses:\n$it")
            }
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