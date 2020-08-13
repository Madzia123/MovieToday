package com.magdalena.movietoday.presentation.movieList.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.magdalena.movietoday.R
import com.magdalena.movietoday.api.movie.Result
import com.magdalena.movietoday.base.BaseRecyclerAdapter
import com.magdalena.movietoday.models.MovieItem
import com.magdalena.movietoday.utils.BASE_URL_POSTER_IMG
import kotlinx.android.synthetic.main.item_movie_details.view.*
import java.lang.ref.WeakReference

class MovieAdapter(private val onResultClicked: (MovieItem) -> Unit) :
    BaseRecyclerAdapter<MovieItem, MovieAdapter.MovieViewHolder>() {

    private var listener: WeakReference<MovieListener>? = null

    fun setListenerMovie(listener: MovieListener) {
        this.listener = WeakReference(listener)
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: MovieViewHolder, item: MovieItem) {
        holder.itemView.apply {
            movie_title.text = item.movie.originalTitle
            movie_release.text = item.movie.releaseDate
            movie_rate.text = item.movie.popularity.toString()
            movie_favorite.isChecked = item.isFavorite
            movie_overview.text = item.movie.overview

            val options = RequestOptions()
                .placeholder(R.drawable.placeholder)
                .dontAnimate()
                .error(R.drawable.placeholder)

            val fullNameMoviePath = BASE_URL_POSTER_IMG + item.movie.posterPath

            Glide.with(context)
                .load(fullNameMoviePath)
                .apply(options)

                .dontAnimate()
                .transform(
                    FitCenter()
                )
                .into(movie_list_poster)

            setOnClickListener {
                onResultClicked(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            inflate(
                parent,
                R.layout.item_movie_details
            )
        )


}