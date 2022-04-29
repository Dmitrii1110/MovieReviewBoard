package com.proect.moviereviewboard.model.repository

interface SharedPrefsRepository {

    fun saveInPrefs(value: String) : String
}