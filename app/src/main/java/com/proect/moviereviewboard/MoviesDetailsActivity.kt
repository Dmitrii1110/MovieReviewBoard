package com.proect.moviereviewboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetailsActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var title2: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overview: TextView
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)
        val id = intent.getIntExtra("id", 0) //вытаскиваем id фильма c первого экрана
        Log.d("MyLog", "id $id")


        title = findViewById(R.id.movies_details_title)
        title2 = findViewById(R.id.movies_details_body_overview_label)
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
        overview = findViewById(R.id.movies_details_body_overview)
        banner = findViewById(R.id.movies_details_image_banner)

        //Создаём  API Interface

        val apiInterface = id?.let { ApiInterface.create().getMoviesDetails(it, "2439cc49a36685a90f1febdc8680c95a")}
        apiInterface?.enqueue(object : Callback<MoviesDetails> {
            override fun onResponse(call: Call<MoviesDetails>?, response: Response<MoviesDetails>?) {
                title.text = response?.body()?.title
                title2.text = response?.body()?.title
                releaseDate.text = response?.body()?.release_date.toString()
                score.text = response?.body()?.vote_average.toString()
                overview.text = response?.body()?.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500" + response?.body()?.backdrop_path).into(banner)
            }

            override fun onFailure(call: Call<MoviesDetails>?, t: Throwable?) {
                Log.d("testLogs", "onFailure : ${t?.message}")
            }

        })
    }

}