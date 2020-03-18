package men.ngopi.zain.datapenduduk.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton
import men.ngopi.zain.datapenduduk.BuildConfig
import men.ngopi.zain.datapenduduk.data.repository.Repository
import men.ngopi.zain.datapenduduk.data.source.local.LocalRepository
import men.ngopi.zain.datapenduduk.data.source.local.room.AppDatabase
import men.ngopi.zain.datapenduduk.data.source.pref.PrefRepository
import men.ngopi.zain.datapenduduk.data.source.remote.RemoteRepository
import men.ngopi.zain.datapenduduk.data.source.remote.retrofit.RetrofitServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db-data-penduduk"
        ).build()
    }

    @Singleton
    @Provides
    fun providesLocalRepository(appDatabase: AppDatabase): LocalRepository {
        return LocalRepository(appDatabase)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val baseUrl: String = BuildConfig.BASE_URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitServices(retrofit: Retrofit): RetrofitServices {
        return retrofit.create(RetrofitServices::class.java)
    }

    @Singleton
    @Provides
    fun providesPrefRepository(): PrefRepository {
        return PrefRepository()
    }

    @Singleton
    @Provides
    fun providesRemoteRepository(services: RetrofitServices): RemoteRepository {
        return RemoteRepository(services)
    }

    @Singleton
    @Provides
    fun providesRepository(
        prefRepository: PrefRepository,
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): Repository {
        return Repository(prefRepository, localRepository, remoteRepository)
    }
}
