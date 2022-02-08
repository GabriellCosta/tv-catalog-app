package me.tigrao.catalog.infra.router.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import me.tigrao.catalog.infra.router.NavigationRoute
import me.tigrao.catalog.infra.router.NavigationRouteDefault
import org.koin.dsl.module

val routerModule = module {
    single {
        Cicerone.create()
    }

    single {
        get<Cicerone<Router>>().router
    }

    single {
        get<Cicerone<Router>>().getNavigatorHolder()
    }

    single<NavigationRoute> { NavigationRouteDefault(get()) }
}
