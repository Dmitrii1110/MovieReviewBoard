package com.proect.moviereviewboard.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.proect.moviereviewboard.data.MovieDetails
import com.proect.moviereviewboard.data.Movies
import com.proect.moviereviewboard.data.Result
import com.proect.moviereviewboard.model.repository.MoviesDBRepository
import com.proect.moviereviewboard.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("TestLogs", "OnResponse Success ${call.toString()}")
                _movies.postValue(response?.body()?.results)
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "onFailure : ${t?.message}")
            }
        })
    }

    /**
     *
     */
    fun getMovieDetails(id: Int) {

        val response = mMoviesRepository.getMovieDetails(id)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>) {
            Log.d("testLogs", "onResponse Success ${call.toString()}")
            _movieDetails.postValue(response?.body())
        }
                override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
            Log.d("testLogs", "onFailure : ${t?.message}")
        }
    })
}
}