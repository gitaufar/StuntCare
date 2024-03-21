package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.raon.MainActivity
import com.example.raon.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfilFragment : Fragment() {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val db = Firebase.database
    val ref = db.getReference(currentUser?.uid!!)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogout: AppCompatButton = view.findViewById(R.id.btnLogout)
        val flMid: FrameLayout = view.findViewById(R.id.flMid)
        val flPop: FrameLayout = view.findViewById(R.id.logout_popup)
        val tNama: TextView = view.findViewById(R.id.Tname)
        val tEmail: TextView = view.findViewById(R.id.Temail)
        val cancel: AppCompatButton = view.findViewById(R.id.cancel)
        val logout: AppCompatButton = view.findViewById(R.id.logout)

        ref.child("nama").get().addOnSuccessListener {
            tNama.text = it.value.toString()
        }
        ref.child("email").get().addOnSuccessListener {
            tEmail.text = it.value.toString()
        }

        logout.setOnClickListener(){
            auth.signOut()
            Intent(requireActivity(), MainActivity::class.java).also{
                startActivity(it)
            }
        }

        cancel.setOnClickListener(){
            flPop.visibility = View.GONE
            flMid.visibility = View.VISIBLE
        }

        btnLogout.setOnClickListener() {
            flMid.visibility = View.GONE
            flPop.visibility = View.VISIBLE
        }
    }


    }