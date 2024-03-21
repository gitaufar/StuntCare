package com.example.stuntcare

import AntiStuntingFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.raon.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        replaceFragment(AntiStuntingFragment())

        val botnav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        botnav.setSelectedItemId(R.id.place_holder)
        botnav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.bottom_profile -> {
                    replaceFragment(ProfilFragment())
                    true
                }

                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.bottom_procare -> {
                    replaceFragment(ProCareFragment())
                    true
                }

                R.id.bottom_stream -> {
                    replaceFragment(StreamFragment())
                    true
                }

                else -> false
            }

        }
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener(){
            botnav.setSelectedItemId(R.id.place_holder)
            replaceFragment(AntiStuntingFragment())
            }
        }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_continer, fragment)
        fragmentTransaction.commit()
    }
    }
