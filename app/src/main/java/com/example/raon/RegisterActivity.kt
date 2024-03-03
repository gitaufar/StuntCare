package com.example.raon


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
//    private lateinit var binding:ActivityRegisterBinding
//    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
//    private lateint var btnReg: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        enableEdgeToEdge()
//        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

//        btnReg = findViewById(R.id.R_btn_1)
        val emailEditText = findViewById<EditText>(R.id.editTextText)
        val passwordEditText = findViewById<EditText>(R.id.editTextText2)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextText3)
        val registerButton = findViewById<Button>(R.id.button)
        val termsCheckBox = findViewById<CheckBox>(R.id.check_id)

//        binding.RBtn1.setOnClickListener{
        registerButton.setOnClickListener{
            val email = emailEditText.text.toString()
            val pass = passwordEditText.text.toString()
            val confirmPass = confirmPasswordEditText.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            val intent = Intent(this@RegisterActivity, RegisterActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@RegisterActivity, task.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this@RegisterActivity, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@RegisterActivity , "Empty Fields Are Not Allowed " , Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}