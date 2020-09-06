package com.dadoufi.foursquare_client.di

import android.content.Context
import androidx.room.Room
import com.dadoufi.foursquare_client.data.local.AppDatabase
import com.dadoufi.foursquare_client.data.remote.ApiInterceptor
import com.dadoufi.foursquare_client.data.remote.WebService
import com.dadoufi.foursquare_client.utils.AppCoroutineDispatchers
import com.dadoufi.foursquare_client.utils.ConnectivityStateManager
import com.dadoufi.foursquare_client.utils.ConnectivityStateManagerImpl
import com.dadoufi.foursquare_client.utils.Constants.DATABASE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideVenuesDao(db: AppDatabase) = db.venuesDao()

    @Singleton
    @Provides
    fun provideVenueDetailsDao(db: AppDatabase) = db.venueDetailsDao()

    @Singleton
    @Provides
    fun provideApiInterceptor() = ApiInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(apiInterceptor: ApiInterceptor) = OkHttpClient.Builder()
        .addInterceptor(apiInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, @BaseUrl baseUrl: String): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideNetworkState(@ApplicationContext context: Context): ConnectivityStateManager =
        ConnectivityStateManagerImpl(context)

    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )
}