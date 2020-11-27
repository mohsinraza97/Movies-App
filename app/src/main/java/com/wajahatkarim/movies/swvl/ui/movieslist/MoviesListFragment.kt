package com.wajahatkarim.movies.swvl.ui.movieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.wajahatkarim.movies.swvl.R
import com.wajahatkarim.movies.swvl.base.BaseFragment
import com.wajahatkarim.movies.swvl.databinding.MoviesListFragmentBinding
import com.wajahatkarim.movies.swvl.utils.gone
import com.wajahatkarim.movies.swvl.utils.visible

class MoviesListFragment : BaseFragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var bi: MoviesListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bi = MoviesListFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProvider).get(MoviesListViewModel::class.java)

        setupViews()
        initObservations()
        viewModel.init()
    }

    fun setupViews() {

    }

    fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is LoadingState -> {
                    bi.apply {
                        recyclerMovies.gone()
                        txtError.gone()
                        progressBar.visible()
                    }
                }

                is ReadAssetsState -> {
                    viewModel.readMoviesDataFromAssets(requireActivity().assets.open("movies.json"))
                }

                is ContentState -> {
                    bi.apply {
                        recyclerMovies.visible()
                        txtError.gone()
                        progressBar.gone()
                    }
                }

                is ErrorState -> {
                    bi.apply {
                        recyclerMovies.gone()
                        txtError.visible()
                        txtError.text = state.message
                        progressBar.gone()
                    }
                }
            }
        }
    }
}