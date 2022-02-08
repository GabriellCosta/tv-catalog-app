package me.tigrao.catalog

import android.app.Application
import dev.tigrao.catalog.infra.network.di.networkImplModule
import me.tigrao.catalog.detail.di.movieDetailModule
import me.tigrao.catalog.infra.router.di.routerModule
import me.tigrao.catalog.movies.di.movieListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@CustomApplication)
            // modules
            modules(movieListModule)
            modules(networkImplModule)
            modules(appModule)
            modules(movieDetailModule)
            modules(routerModule)
        }
    }
}
