package com.magdalena.movietoday.di

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

open class BaseViewModel : ViewModel() {

    var navigate: NavController? = null

}