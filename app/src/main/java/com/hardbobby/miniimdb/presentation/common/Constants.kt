package com.hardbobby.miniimdb.presentation.common

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
object Constants {

    val tabMenu = listOf("Popular", "Top Rated", "Favourite")
    val EXTRA_ID_MOVIE = "EXTRA_ID_MOVIE"

    sealed class PageType {
        object PopularPage : PageType()
        object TopRatedPage : PageType()
        object FavoritePage : PageType()
    }
}