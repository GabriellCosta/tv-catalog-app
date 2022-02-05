package me.tigrao.catalog.infra.router

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

internal class NavigationRouteDefault(
    private val router: Router
) : NavigationRoute {

    override fun navigateByRoute(screen: Screen) {
        router.navigateTo(screen)
    }
}
