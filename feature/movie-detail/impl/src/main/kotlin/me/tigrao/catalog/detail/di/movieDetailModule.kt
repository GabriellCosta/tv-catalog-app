package me.tigrao.catalog.detail.di

import me.tigrao.catalog.detail.MovieDetailNavigator
import me.tigrao.catalog.detail.MovieDetailNavigatorDefault
import me.tigrao.catalog.detail.data.MovieDetailApi
import me.tigrao.catalog.detail.data.MovieDetailDataSource
import me.tigrao.catalog.detail.domain.EpisodeListStateUseCase
import me.tigrao.catalog.detail.domain.EpisodeListStateUseCaseDefault
import me.tigrao.catalog.detail.domain.FetchEpisodesListUseCase
import me.tigrao.catalog.detail.domain.FetchEpisodesListUseCaseDefault
import me.tigrao.catalog.detail.domain.mapper.EpisodeListSuccessMapper
import me.tigrao.catalog.detail.domain.mapper.EpisodesListErrorMapper
import me.tigrao.catalog.detail.presententation.MovieDetailStateViewFactory
import me.tigrao.catalog.detail.presententation.MovieDetailViewModel
import me.tigrao.catalog.detail.presententation.mapper.EpisodeModelToModelUiMapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val movieDetailModule = module {
    viewModel {
        MovieDetailViewModel(get(), get())
    }

    factory<EpisodeListStateUseCase> {
        EpisodeListStateUseCaseDefault(get(), get(), get())
    }

    factory {
        EpisodeModelToModelUiMapper(androidContext().resources)
    }

    factory { MovieDetailStateViewFactory(androidContext().resources) }

    single {
        get<Retrofit>().create(MovieDetailApi::class.java)
    }

    factory {
        MovieDetailDataSource(get())
    }

    factory<FetchEpisodesListUseCase> { FetchEpisodesListUseCaseDefault(get(), get(), get()) }

    factory { EpisodeListSuccessMapper() }

    factory { EpisodesListErrorMapper() }

    factory<MovieDetailNavigator> { MovieDetailNavigatorDefault(get()) }
}
