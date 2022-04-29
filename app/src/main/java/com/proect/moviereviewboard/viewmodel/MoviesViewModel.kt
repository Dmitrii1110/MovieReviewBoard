package com.proect.moviereviewboard.viewmodel

import com.proect.moviereviewboard.model.repository.MoviesDBRepository
import com.proect.moviereviewboard.model.repository.MoviesDBRepositoryImpl

class MoviesViewModel {

    private val mMoviesRepository : MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies(): String {
        return mMoviesRepository.getMovies()
    }
}