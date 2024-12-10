package com.example.tifinbox.dashboard.bottomnav

import com.example.tifinbox.R

sealed class BottomBarScreen(
    val routes: String,
    val title: String,
    val icon: Int,
) {

    // for home
    object Home: BottomBarScreen(
        routes = "home",
        title = "Home",
        icon = R.drawable.bottom_home,
    )

    // for cart
    object Cart: BottomBarScreen(
        routes = "cart",
        title = "Cart",
        icon = R.drawable.bottom_cart
    )

    // for search
    object Search: BottomBarScreen(
        routes = "search",
        title = "Search",
        icon = R.drawable.bottom_search
    )

    object Profile: BottomBarScreen(
        routes = "profile",
        title = "Profile",
        icon = R.drawable.bottom_profile
    )

}