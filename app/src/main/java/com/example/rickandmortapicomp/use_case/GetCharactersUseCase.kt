package com.example.rickandmortapicomp.use_case

import androidx.paging.PagingData
import com.example.rickandmortapicomp.data.remote.models.characters.Character
import com.example.rickandmortapicomp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharactersRepository,
    private val name: String? = null,
) {
    suspend operator fun invoke(): Flow<PagingData<Character>> {
        return characterRepository.getAllCharacters(name)
    }
}
