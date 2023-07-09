package com.jb.shaadiassignment.di

import android.app.Application
import androidx.room.Room
import com.jb.shaadiassignment.data.local.UserRoomDb
import com.jb.shaadiassignment.data.remote.ApiService
import com.jb.shaadiassignment.data.repository.LocalRepositoryImpl
import com.jb.shaadiassignment.data.repository.NetworkRepositoryImpl
import com.jb.shaadiassignment.domain.repository.LocalRepository
import com.jb.shaadiassignment.domain.repository.NetworkRepository
import com.jb.shaadiassignment.util.ApiLogger
import com.jb.shaadiassignment.util.Constants
import com.jb.shaadiassignment.util.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): ApiService {
        val client = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor(ApiLogger())
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(loggingInterceptor)
        return Retrofit
            .Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ApiService::class.java)

    }


    @Provides
    @Singleton
    fun provideNetworkRepo(api: ApiService): NetworkRepository {
        return NetworkRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideRoomDb(app: Application): UserRoomDb {
        return Room
            .databaseBuilder(app, UserRoomDb::class.java, Constants.ROOM_DB_NAME)
            .build()
    }


    @Provides
    @Singleton
    fun provideLocalRepo(db: UserRoomDb): LocalRepository {
        return LocalRepositoryImpl(db.dao())
    }


    @Provides
    @Singleton
    fun provideNetworkStatus(app : Application): NetworkUtils{
        return NetworkUtils(app)
    }


}