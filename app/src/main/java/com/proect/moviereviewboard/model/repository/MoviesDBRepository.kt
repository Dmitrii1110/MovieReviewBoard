package com.proect.moviereviewboard.model.repository

import com.proect.moviereviewboard.data.MovieDetails
import com.proect.moviereviewboard.data.Movies
import retrofit2.Call

/**
 * Repository providers information taken from MovieDb API
 */

interface MoviesDBRepository {

    /**
     * Returns list of popular [Movies]
     */
    fun getMovies(): Call<Movies>

    /**
     * Returns information for a single movie by returning [MovieDetails]
     * @param id - identification number of the needed movie
     */

    fun getMovieDetails(id: Int): Call<MovieDetails>

}