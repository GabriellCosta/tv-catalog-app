package me.tigrao.catalog.detail

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.tigrao.catalog.detail.view.MovieDetailArgs
import me.tigrao.catalog.detail.view.MovieDetailFragment
import me.tigrao.catalog.infra.key.BundleKeyArgFactory
import me.tigrao.catalog.infra.router.NavigationRoute

internal class MovieDetailNavigatorDefault(
    private val navigationRoute: NavigationRoute,
) : MovieDetailNavigator {
    override fun openMovieDetail(args: MovieDetailArgs) {
        val fragment: Fragment = MovieDetailFragment()
        fragment.arguments = BundleKeyArgFactory.create(args)

        navigationRoute.navigateByRoute(
            FragmentScreen { fragment }
        )
    }
}
