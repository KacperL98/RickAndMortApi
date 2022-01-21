package com.example.rickandmortapicomp.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortapicomp.data.remote.models.characters.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(name: String? = null): Flow<PagingData<Character>>
}
