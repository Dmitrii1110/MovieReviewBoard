package com.proect.moviereviewboard.model.repository

import com.proect.moviereviewboard.Constants
import com.proect.moviereviewboard.data.MovieDetails
import com.proect.moviereviewboard.data.Movies
import com.proect.moviereviewboard.model.apis.ApiInterface
import retrofit2.Call

/**
 * Repository provides information taken from MovieDb API
 */
class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY, "en-US", 1)
    }

    override fun getMovieDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getMoviesDetails(id, Constants.API_KEY)
    }
}