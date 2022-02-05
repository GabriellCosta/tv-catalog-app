package me.tigrao.catalog.repo

import me.tigrao.catalog.repo.data.RepoDataSource
import me.tigrao.catalog.repo.data.RepositoryErrorModelToUiMapper
import me.tigrao.catalog.repo.data.api.RepoApi
import me.tigrao.catalog.repo.domain.FetchRepositoryErrorMapper
import me.tigrao.catalog.repo.domain.FetchRepositorySuccessMapper
import me.tigrao.catalog.repo.domain.FetchRepositoryUseCase
import me.tigrao.catalog.repo.domain.FetchRepositoryUseCaseImpl
import me.tigrao.catalog.repo.presentation.PagerProvider
import me.tigrao.catalog.repo.presentation.RepoViewModel
import me.tigrao.catalog.repo.presentation.StateViewFactory
import me.tigrao.catalog.repo.view.adapter.LayoutManagerFactory
import me.tigrao.catalog.repo.view.adapter.RepoAdapter
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
