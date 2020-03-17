package men.ngopi.zain.datapenduduk.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import men.ngopi.zain.datapenduduk.ui.home.HomeActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(homeActivity: HomeActivity)

}