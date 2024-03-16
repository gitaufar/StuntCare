package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.raon.MainActivity
import com.example.raon.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {
    private lateinit var  firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogout: Button = view.findViewById(R.id.logout)
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val db = Firebase.database
        val ref = db.getReference(currentUser?.uid!!)
        val text: TextView = view.findViewById(R.id.text)

        ref.child("nama").get().addOnSuccessListener {
            text.text = "Good Morning ${it.value}"
        }

        btnLogout.setOnClickListener(){
            auth.signOut()
            Intent(requireActivity(),MainActivity::class.java).also{
                startActivity(it)
            }
        }
    }

}