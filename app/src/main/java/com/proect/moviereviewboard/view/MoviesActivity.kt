package com.proect.moviereviewboard.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proect.moviereviewboard.*
import com.proect.moviereviewboard.data.Result
import com.proect.moviereviewboard.view.adapters.CustomAdapter
import com.proect.moviereviewboard.viewmodel.MoviesViewModel

class MoviesActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {

    private val mViewModel: MoviesViewModel = MoviesViewModel()
    private val mMoviesViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initViews()
        initObservers()
        mViewModel.getMovies()

    }

        private fun initObservers() {
            mViewModel.apply {
                movies.observe(this@MoviesActivity) {
                    mMoviesAdapter = CustomAdapter(it as List<Result>?, this@MoviesActivity)
                    mMoviesRecycler.adapter = mMoviesAdapter
                }
            }
        }

        private fun initViews() {
            mMoviesRecycler = findViewById<RecyclerView>(R.id.recyclerview)
            mMoviesRecycler.layoutManager = GridLayoutManager(this, 2)
        }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }

}