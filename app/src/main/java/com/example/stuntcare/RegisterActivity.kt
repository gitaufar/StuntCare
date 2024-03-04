package com.example.raon




import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.raon.R
import com.example.raon.databinding.ActivityRegisterBinding
import com.example.stuntcare.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.RBtn1.setOnClickListener{
            val email = binding.RTxtEmail.text.toString()
            val pass = binding.RTxtPassword.text.toString()
            val confirmPass = binding.RTxtConfirmPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass == confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this , LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this , "Password is not matching" , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this , "Empty Fields Are Not Allowed !!" , Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}