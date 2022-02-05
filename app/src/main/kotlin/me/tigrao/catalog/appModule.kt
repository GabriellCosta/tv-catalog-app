package me.tigrao.catalog

import dev.tigrao.catalog.infra.network.NetworkBuilder
import okhttp3.Interceptor
import org.koin.dsl.module
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind

val appModule = module {
    single {
        NetworkBuilder(BuildConfig.API_URL)
    }

    factory {
        HttpLoggingInterceptor()
            .also {
                if (BuildConfig.DEBUG) {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }
            }
    } bind Interceptor::class
}
