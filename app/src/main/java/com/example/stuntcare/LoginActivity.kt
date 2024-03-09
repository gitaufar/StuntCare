//package com.example.stuntcare
package com.example.raon



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.stuntcare.AntiStunting2
import com.example.stuntcare.PersonalData1
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.database

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginn)
        val auth = FirebaseAuth.getInstance()
        val database = Firebase.database
        val btnLogin: AppCompatButton = findViewById(R.id.login)
        val etPass : EditText = findViewById(R.id.etPasswordLogin)
        val etEmail: EditText = findViewById(R.id.etEmailLogin)
        val register: TextView = findViewById(R.id.register)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val btnGoogle: AppCompatButton = findViewById(R.id.btnGoogle)

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        register.setOnClickListener(){
            Intent(this, RegisterActivity::class.java).also{
                startActivity(it)
            }
        }

//        val loginButton = findViewById<Button>(R.id.login)
//        loginButton.setOnClickListener {
//            // Membuat Intent untuk berpindah ke halaman AntiStunting2
//            val intent = Intent(this, AntiStunting2::class.java)
//            // Memulai aktivitas baru dengan Intent
//            startActivity(intent)
//        }

        btnGoogle.setOnClickListener(){
            signInWithGoogle()
        }

        btnLogin.setOnClickListener(){
            val email: String = etEmail.text.toString()
            val pass: String = etPass.text.toString()
            
            if(email.isNotEmpty() && pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(OnCompleteListener{
                    if(it.isSuccessful){
                        val currentUser = firebaseAuth.currentUser
                        val ref = database.getReference(currentUser?.uid!!)
                        ref.child("nama").get().addOnSuccessListener {
                            if(ref.child("nama").get().equals("")) {
                                val intent = Intent(this, AntiStunting2::class.java)
                                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                                finish()
                            } else {
                                val intent = Intent(this, PersonalData1::class.java)
                                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                                startActivity(intent)
                                finish()
                            }
                        }.addOnFailureListener{
                            Log.e("firebase", "Error getting data", it)
                        }

                    } else {
                        Toast.makeText(this, "wrong email or password", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "email and password cant be empty", Toast.LENGTH_SHORT).show()
            }

        }
        
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google Sign-In failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    //TODO: Handle successful sign-in
                    Intent(this,HomeActivity::class.java).also{
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }

}

