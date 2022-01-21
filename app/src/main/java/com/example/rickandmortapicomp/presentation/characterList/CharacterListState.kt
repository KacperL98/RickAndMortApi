package com.example.rickandmortapicomp.presentation.characterList

import androidx.paging.PagingData
import com.example.rickandmortapicomp.data.remote.models.characters.Character
import kotlinx.coroutines.flow.Flow

data class CharacterListState(
    var isLoading: Boolean = false,
    val dataList: Flow<PagingData<Character>>? = null,
    val errorMessage: String = ""
)
