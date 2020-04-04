package men.ngopi.zain.aroundme.di

import men.ngopi.zain.aroundme.data.repository.Repository
import men.ngopi.zain.aroundme.data.repository.RepositoryImpl
import men.ngopi.zain.aroundme.data.source.MockRemoteRepository
import men.ngopi.zain.aroundme.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}


val repositoryModule = module {
    single { MockRemoteRepository() }
    single { Repository(get()) }
}