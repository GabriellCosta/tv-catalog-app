package me.tigrao.catalog

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import dev.tigrao.catalog.infra.network.di.networkImplModule
import me.tigrao.catalog.movies.movieListModule
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
        }
    }

    private fun prepareNavigation() {
        val cicerone = Cicerone.create()
        val router = cicerone.router
        val navHolder = cicerone.getNavigatorHolder()
    }
}
