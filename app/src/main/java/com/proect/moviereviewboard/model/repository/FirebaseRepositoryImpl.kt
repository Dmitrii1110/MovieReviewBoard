package com.proect.moviereviewboard.model.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.proect.moviereviewboard.data.User

class FirebaseRepositoryImpl : FirebaseRepository {

    private var database: DatabaseReference = Firebase.database.reference // создал объект для записи в БД

    override fun updateUserData(firebaseUser: User, uid: String) {
        database.child("users").child(uid).setValue(firebaseUser) //сохраняем нашего пользователя в DataBase Realtime
    }
}