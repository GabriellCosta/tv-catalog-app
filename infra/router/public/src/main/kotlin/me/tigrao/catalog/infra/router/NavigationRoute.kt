package me.tigrao.catalog.infra.router

import com.github.terrakok.cicerone.Screen

interface NavigationRoute {

    fun navigateByRoute(screen: Screen)
}
