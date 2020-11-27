package com.wajahatkarim.movies.swvl.ui.movieslist

sealed class MoviesListUiState

object LoadingState: MoviesListUiState()
object ContentState: MoviesListUiState()
class ErrorState(val message: String) : MoviesListUiState()

object ReadAssetsState: MoviesListUiState()