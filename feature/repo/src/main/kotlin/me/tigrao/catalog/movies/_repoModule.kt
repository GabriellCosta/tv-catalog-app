package me.tigrao.catalog.movies

import me.tigrao.catalog.movies.data.RepoDataSource
import me.tigrao.catalog.movies.data.RepositoryErrorModelToUiMapper
import me.tigrao.catalog.movies.data.api.RepoApi
import me.tigrao.catalog.movies.domain.FetchRepositoryErrorMapper
import me.tigrao.catalog.movies.domain.FetchRepositorySuccessMapper
import me.tigrao.catalog.movies.domain.FetchRepositoryUseCase
import me.tigrao.catalog.movies.domain.FetchRepositoryUseCaseImpl
import me.tigrao.catalog.movies.presentation.PagerProvider
import me.tigrao.catalog.movies.presentation.RepoViewModel
import me.tigrao.catalog.movies.presentation.StateViewFactory
import me.tigrao.catalog.movies.view.adapter.LayoutManagerFactory
import me.tigrao.catalog.movies.view.adapter.RepoAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val repoModule = module {

    single {
        get<Retrofit>().create(RepoApi::class.java)
    }

    single {
        RepoAdapter()
    }

    single {
        RepoDataSource(get(), get())
    }

    factory {
        RepositoryErrorModelToUiMapper(androidContext().resources)
    }

    viewModel {
        RepoViewModel(get(), get())
    }

    factory {
        StateViewFactory(androidContext().resources)
    }

    factory {
        PagerProvider(get())
    }

    single {
        LayoutManagerFactory()
    }

    factory {
        FetchRepositorySuccessMapper()
    }

    factory {
        FetchRepositoryErrorMapper()
    }

    factory<FetchRepositoryUseCase> {
        FetchRepositoryUseCaseImpl(get(), get(), get())
    }
}
