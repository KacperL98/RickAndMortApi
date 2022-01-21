package com.example.rickandmortapicomp.data.remote

import com.example.rickandmortapicomp.data.remote.models.characters.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getListCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ): CharactersListDto
}
