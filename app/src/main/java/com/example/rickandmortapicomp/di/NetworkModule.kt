package com.example.rickandmortapicomp.di

import com.example.rickandmortapicomp.data.remote.ApiService
import com.example.rickandmortapicomp.data.repository.CharactersListRepositoryImpl
import com.example.rickandmortapicomp.domain.repository.CharactersRepository
import com.example.rickandmortapicomp.use_case.GetCharactersUseCase
import com.example.rickandmortapicomp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val json = Json { ignoreUnknownKeys = true }

    @ExperimentalSerializationApi
    private val converter = json.asConverterFactory("application/json".toMediaType())

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun providesAPI(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        return Retrofit.Builder()
            .addConverterFactory(converter)
            .baseUrl(BASE_URL)
            .client(client.build())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesCharacterRepository(apiService: ApiService): CharactersRepository =
        CharactersListRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun providesCharactersUseCase(charactersRepository: CharactersRepository) =
        GetCharactersUseCase(charactersRepository)

}
