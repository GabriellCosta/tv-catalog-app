package dev.tigrao.catalog.infra.network.di

import com.squareup.moshi.Moshi
import dev.tigrao.catalog.infra.network.NetworkService
import dev.tigrao.catalog.infra.network.OkhttpClientFactory
import okhttp3.Interceptor
import org.koin.dsl.module

val networkImplModule = module {

    single {
        Moshi.Builder().build()
    }

    single {
        NetworkService(
            networkBuilder = get(),
            okhttpClientFactory = get(),
            moshi = get(),
            callAdapterFactoryList = getAll(),
        ).createRetrofitInstance()
    }

    single {
        val interceptor = getAll<Interceptor>()
        OkhttpClientFactory(interceptor)
    }
}
