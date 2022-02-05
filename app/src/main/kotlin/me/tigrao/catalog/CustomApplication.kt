package me.tigrao.catalog

import android.app.Application
import dev.tigrao.catalog.infra.network.di.networkImplModule
import me.tigrao.catalog.repo.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@CustomApplication)
            // modules
            modules(repoModule)
            modules(networkImplModule)
            modules(appModule)
        }
    }
}
