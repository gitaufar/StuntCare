package com.example.stuntcare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.raon.HomeActivity
import com.example.raon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val botnav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        botnav.setSelectedItemId(R.id.bottom_profile)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener(){
           Intent(this,AntiStunting2::class.java).also{
               startActivity(it)
           }
        }

        botnav.setOnItemSelectedListener { it ->
            when(it.itemId){
                R.id.bottom_home -> {
                    Intent(this,HomeActivity::class.java).also{
                        startActivity(it)
                        finish()
                    }
                    true
                }
                R.id.bottom_procare -> {
                    Intent(this,HomeActivity::class.java).also{
                        startActivity(it)
                        finish()
                    }
                    true
                }
                R.id.bottom_stream -> {
                    Intent(this,HomeActivity::class.java).also{
                        startActivity(it)
                        finish()
                    }
                    true
                }
                else -> false
            }
        }
    }
}