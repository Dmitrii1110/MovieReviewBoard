package com.proect.moviereviewboard.model.repository

import com.proect.moviereviewboard.data.User

interface FirebaseRepository {
    fun updateUserData(firebaseUser: User, uid: String)
}