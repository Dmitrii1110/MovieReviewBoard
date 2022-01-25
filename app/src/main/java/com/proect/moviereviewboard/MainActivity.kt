package com.proect.moviereviewboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult( //создали объект авторизации
        FirebaseAuthUIActivityResultContract()
    ) {res ->
        this.onSignInResult(res)  //запуск самого экрана

    }

    private lateinit var database: DatabaseReference  //создали объект для записи в БД

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Firebase.database.reference   //инициализация базы данных

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()) // создали список регистраицй, которые мы используем

// Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()  //создали интент для экрана firebase auth
        signInLauncher.launch(signInIntent)  //запустили экран firebase auth
    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse // результат с экрана Firebase auth
        if (result.resultCode == RESULT_OK) { //если результат ОК

            Log.d("MyLog", "Registration Activity firebaseUser success ${response?.email}")
            // Successfully signed in
            val authUser = FirebaseAuth.getInstance().currentUser //создаем объект текущего пользователя Firebase Auth
            authUser?.let { //если он существует мы сохраняем его в базу данных
                val email = it.email.toString() //извлекаем емейл пользователя
                val uid = it.uid //извлекаем uid пользователя
                val firebaseUser = User(email, uid) // создаем новый объект user
                Log.d("MyLog", "Registration Activity firebaseUser $firebaseUser")
                database.child("users").child(uid).setValue(firebaseUser) //сохраняем нашего пользователя в DataBase Realtime

                val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
                    startActivity(intentToAnotherScreen)

            }



        } else { // если результат не ОК то должны обработать ошибку
            Log.d("MyLog", "Registration Activity failure")
            Toast.makeText(this@MainActivity, "Something wrong with registration", Toast.LENGTH_SHORT).show()

            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
    }
