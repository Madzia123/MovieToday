package com.magdalena.movietoday.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavController


open class BaseFragment : Fragment() {

    private var toolbarInteraction: ToolbarInteraction? = null
    protected var navigate: NavController? = null

    protected var navigationBack: Boolean = false
        set(value) {
            field = value
            showNavigationBack(value)
        }

    protected var toolbarTitleRes: Int = 0
        set(value) {
            field = value
            toolbarTitle = getString(field)
        }

    protected var toolbarTitle: String = ""
        set(value) {
            field = value
            toolbarInteraction?.topActionBar?.title = value
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigate = (context as? Navigation)?.getNavigationController()
        toolbarInteraction = activity as ToolbarInteraction
    }

    override fun onDetach() {
        super.onDetach()
        navigate = null
        toolbarInteraction = null
    }

    private fun showNavigationBack(isShow: Boolean) {
        toolbarInteraction?.topActionBar?.apply {
            setDisplayShowHomeEnabled(isShow)
            setDisplayHomeAsUpEnabled(isShow)
            setDisplayUseLogoEnabled(isShow)
        }
    }

}