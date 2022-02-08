package me.tigrao.catalog.movies

import me.tigrao.catalog.movies.data.MovieListDatasourceFactory
import me.tigrao.catalog.movies.data.MovieListErrorModelToUiMapper
import me.tigrao.catalog.movies.data.api.MovieListApi
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListErrorMapper
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListSearchSuccessMapper
import me.tigrao.catalog.movies.domain.FetchMovieListSearchUseCase
import me.tigrao.catalog.movies.domain.FetchMovieListSearchUseCaseDefault
import me.tigrao.catalog.movies.domain.mapper.FetchMovieListSuccessMapper
import me.tigrao.catalog.movies.domain.FetchMovieListUseCase
import me.tigrao.catalog.movies.domain.FetchMovieListUseCaseImpl
import me.tigrao.catalog.movies.domain.FetchMovieListWithoutSearchUseCase
import me.tigrao.catalog.movies.domain.FetchMovieListWithoutSearchUseCaseDefault
import me.tigrao.catalog.movies.presentation.MovieListViewModel
import me.tigrao.catalog.movies.presentation.PagerProvider
import me.tigrao.catalog.movies.presentation.StateViewFactory
import me.tigrao.catalog.movies.view.adapter.LayoutManagerFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val movieListModule = module {

    single {
        get<Retrofit>().create(MovieListApi::class.java)
    }

    single {
        MovieListDatasourceFactory(get(), get())
    }

    factory {
        MovieListErrorModelToUiMapper(androidContext().resources)
    }

    viewModel {
        MovieListViewModel(get(), get())
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
        FetchMovieListSearchSuccessMapper(get())
    }

    factory {
        FetchMovieListErrorMapper()
    }

    factory<FetchMovieListUseCase> {
        FetchMovieListUseCaseImpl(get(), get())
    }

    factory<FetchMovieListSearchUseCase> {
        FetchMovieListSearchUseCaseDefault(get(), get(), get())
    }

    factory<FetchMovieListWithoutSearchUseCase> {
        FetchMovieListWithoutSearchUseCaseDefault(get(), get(), get())
    }
}
