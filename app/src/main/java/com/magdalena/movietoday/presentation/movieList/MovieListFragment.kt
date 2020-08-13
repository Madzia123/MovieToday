package com.magdalena.movietoday.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.magdalena.movietoday.R
import com.magdalena.movietoday.base.BaseFragment
import com.magdalena.movietoday.presentation.movieList.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : BaseFragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var adpter: MovieAdapter


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
    }

    private fun setupRecycler() {
        adpter = MovieAdapter(viewModel::onMovieClicked)
        movie_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adpter
        }
    }




}