package com.proect.moviereviewboard.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.proect.moviereviewboard.R
import com.proect.moviereviewboard.data.User
import com.proect.moviereviewboard.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private val mMainActivityViewModel : MainActivityViewModel = MainActivityViewModel()

    private val signInLauncher = registerForActivityResult( //создали объект авторизации
        FirebaseAuthUIActivityResultContract()
    ) { resultCallback ->
        this.onSignInResult(resultCallback)  //запуск самого экрана

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRegistrationScreen()
    }

    /**
     * We make a call to firebase auth api to show dialog for registration
     */
    private fun openRegistrationScreen() {
        val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
        startActivity(intentToAnotherScreen)

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
        when(result.resultCode) {
            RESULT_OK -> {
            val authUser =
                FirebaseAuth.getInstance().currentUser //создаем объект текущего пользователя Firebase Auth
            authUser?.let { //если он существует мы сохраняем его в базу данных
                val email = it.email.toString() //извлекаем емейл пользователя
                val uid = it.uid //извлекаем uid пользователя
                val firebaseUser = User(email, uid) // создаем новый объект user

                mMainActivityViewModel.updateUserData(firebaseUser, uid)


                val intentToAnotherScreen = Intent(this, MoviesActivity::class.java)
                startActivity(intentToAnotherScreen)

            }

        }
        RESULT_CANCELED -> {
            Toast.makeText(this@MainActivity, "Something wrong with registration", Toast.LENGTH_SHORT).show()
        }
        else -> {

        }
    }
    }
}
