package com.magdalena.movietoday.presentation.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.magdalena.movietoday.R
import com.magdalena.movietoday.api.movieDetails.MovieDetailsResponse
import com.magdalena.movietoday.base.BaseFragment
import com.magdalena.movietoday.presentation.movieList.MovieListViewModel
import com.magdalena.movietoday.presentation.movieList.adapter.MovieAdapter
import com.magdalena.movietoday.utils.BASE_URL_POSTER_IMG
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.item_movie_details.view.*

class MovieDetailsFragment : BaseFragment() {

    var isFavoriteMovie: Boolean = false

    private lateinit var viewModel: MovieDetailsViewModel

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        viewModel.navigate = navigate
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.apply {
            showMovieDetails(args.movieId)

            result.observe(viewLifecycleOwner, Observer {
                setMovieDetails(it)
            })

        }
    }

    override fun onResume() {
        super.onResume()
        navigationBack = true
    }

    private fun setMovieDetails(result: MovieDetailsResponse) {
        toolbarTitle = result.originalTitle


        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .dontAnimate()
            .error(R.drawable.placeholder)


        val fullNameMoviePath = BASE_URL_POSTER_IMG + result.posterPath

        Glide.with(requireContext())
            .load(fullNameMoviePath)
            .apply(options)

            .dontAnimate()
            .transform(
                FitCenter()
            )
            .into(movie_poster)
    }
}