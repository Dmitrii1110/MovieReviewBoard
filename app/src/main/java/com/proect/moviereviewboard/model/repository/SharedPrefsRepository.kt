package com.proect.moviereviewboard.model.repository

/**
 * Manipulating the data with the local storage shared preferences
 */
interface SharedPrefsRepository {

    /**
     * Saving data in the shared preferences
     */
    fun saveInPrefs(key: String)

    /**
     * Getting data out of the shared preferences
     */
    fun getFromPrefs(key: String) : String
}