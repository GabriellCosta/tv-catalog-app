package me.tigrao.catalog.movies

import me.tigrao.catalog.movies.data.RepoDataSource
import me.tigrao.catalog.movies.data.RepositoryErrorModelToUiMapper
import me.tigrao.catalog.movies.data.api.MovieListApi
import me.tigrao.catalog.movies.domain.FetchMovieListErrorMapper
import me.tigrao.catalog.movies.domain.FetchMovieListSuccessMapper
import me.tigrao.catalog.movies.domain.FetchMovieListUseCase
import me.tigrao.catalog.movies.domain.FetchMovieListUseCaseImpl
import me.tigrao.catalog.movies.presentation.PagerProvider
import me.tigrao.catalog.movies.presentation.MoviewListViewModel
import me.tigrao.catalog.movies.presentation.StateViewFactory
import me.tigrao.catalog.movies.view.adapter.LayoutManagerFactory
import me.tigrao.catalog.movies.view.adapter.MoviewListAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val movieListModule = module {

    single {
        get<Retrofit>().create(MovieListApi::class.java)
    }

    single {
        MoviewListAdapter()
    }

    single {
        RepoDataSource(get(), get())
    }

    factory {
        RepositoryErrorModelToUiMapper(androidContext().resources)
    }

    viewModel {
        MoviewListViewModel(get(), get())
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
        FetchMovieListSuccessMapper()
    }

    factory {
        FetchMovieListErrorMapper()
    }

    factory<FetchMovieListUseCase> {
        FetchMovieListUseCaseImpl(get(), get(), get())
    }
}
