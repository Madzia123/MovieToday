package com.magdalena.movietoday.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.magdalena.movietoday.R
import com.magdalena.movietoday.api.movie.Result
import com.magdalena.movietoday.base.BaseFragment
import com.magdalena.movietoday.database.FavoriteMovie
import com.magdalena.movietoday.models.MovieItem
import com.magdalena.movietoday.presentation.movieList.adapter.MovieAdapter
import com.magdalena.movietoday.presentation.movieList.adapter.MovieListener
import com.magdalena.movietoday.utils.searchMovie
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : BaseFragment(), MovieListener {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var adpter: MovieAdapter

    private var movieList: MutableList<MovieItem> = mutableListOf()
    private var resultMovies: MutableList<Result> = mutableListOf()
    private var favoriteMovieId: MutableList<FavoriteMovie> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.navigate = navigate
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewModel.getNowMovies()
        viewModel.getFavoriteMovies()
        setupObservers()
        movie_search.searchMovie {
            if (it.isNotEmpty()) {
                viewModel.getFavoriteMovies()
                viewModel.searchMovie(it)
            } else {
                clearData()
            }
        }
    }

    private fun setupRecycler() {
        adpter = MovieAdapter(viewModel::onMovieClicked)
        movie_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adpter
        }

        adpter.setListenerMovie(this)

    }

    private fun setupObservers() {
        viewModel.apply {

            progressShow.observe(viewLifecycleOwner, Observer {
                progress_bar_movie.isVisible = it
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
            favoriteMovies.observe(viewLifecycleOwner, Observer {
                favoriteMovieId.addAll(it)
            })

            results.observe(viewLifecycleOwner, Observer {
                clearData()
                resultMovies.addAll(it)

                Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_LONG).show()
                movieList = initMovieList(resultMovies, favoriteMovieId)
                adpter.data = movieList
            })


        }
    }


    private fun initMovieList(
        result: MutableList<Result>,
        favoriteMovieId: MutableList<FavoriteMovie>
    ): MutableList<MovieItem> {

        val movies: MutableList<MovieItem> = mutableListOf()

        result.forEach { movie ->
            val favoriteMovie =
                favoriteMovieId.firstOrNull() { movie.id == it.movieId }
            val isFavoriteMovie = favoriteMovie != null
            val movieItem = MovieItem(movie, isFavoriteMovie)
            movies.add(movieItem)
        }
        return movies
    }

    fun clearData() {
        movieList.clear()
        adpter.clearList()
    }

    override fun isFavoriteMovie(isFavorite: Boolean, movieId: Long) {

        if (isFavorite){
            val favoriteMovie = FavoriteMovie(movieId = movieId)
            viewModel.saveFavoriteMovie(favoriteMovie)
        }else {
            viewModel.deleteFavoriteMovie(movieId = movieId)
        }

    }

}