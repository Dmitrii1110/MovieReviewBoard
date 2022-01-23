package com.proect.moviereviewboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val registerButton = findViewById<Button>(R.id.main_activity_register_button)

        registerButton.setOnClickListener {
            val intentToAnotherScreen = Intent(this, RegistrationActivity::class.java)
                startActivity(intentToAnotherScreen)
            }
        }

    override fun onStop() {
        super.onStop()
        Log.d("MyLog", "MyLog")
    }
    }
