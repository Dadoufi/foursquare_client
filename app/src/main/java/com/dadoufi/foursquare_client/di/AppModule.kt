package com.dadoufi.foursquare_client.di

import android.content.Context
import androidx.room.Room
import com.dadoufi.foursquare_client.data.local.AppDatabase
import com.dadoufi.foursquare_client.data.remote.ApiInterceptor
import com.dadoufi.foursquare_client.data.remote.WebService
import com.dadoufi.foursquare_client.utils.ConnectivityStateManager
import com.dadoufi.foursquare_client.utils.ConnectivityStateManagerImpl
import com.dadoufi.foursquare_client.utils.Constants.BASE_URL
import com.dadoufi.foursquare_client.utils.Constants.DATABASE_NAME
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit) = retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideNetworkState(@ApplicationContext context: Context): ConnectivityStateManager =
        ConnectivityStateManagerImpl(context)
}