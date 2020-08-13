package com.magdalena.movietoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.magdalena.movietoday.base.Navigation
import com.magdalena.movietoday.base.ToolbarInteraction

class MainActivity : AppCompatActivity(), Navigation, ToolbarInteraction {
    override val topActionBar: ActionBar?
        get() =  supportActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavigationController(): NavController = findNavController(R.id.nav_host_fragment)




}