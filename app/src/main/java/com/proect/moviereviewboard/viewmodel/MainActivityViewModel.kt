package com.proect.moviereviewboard.viewmodel

import com.proect.moviereviewboard.data.User
import com.proect.moviereviewboard.model.repository.FirebaseRepository
import com.proect.moviereviewboard.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {

    private val mFirebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebaseUser: User, uid: String) {
        mFirebaseRepository.updateUserData(firebaseUser, uid)
    }
}