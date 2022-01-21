package com.example.rickandmortapicomp.data.repository

import android.net.Uri
import androidx.paging.*
import com.example.rickandmortapicomp.data.remote.ApiService
import com.example.rickandmortapicomp.data.remote.models.characters.Character
import com.example.rickandmortapicomp.data.remote.models.characters.mappers.toCharacter
import com.example.rickandmortapicomp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersListRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {
    override suspend fun getAllCharacters(name: String?): Flow<PagingData<Character>> {

        return Pager(PagingConfig(pageSize = 20)) {
            CharactersListPagingSource(apiService = apiService, name = name)
        }.flow
    }
}

class CharactersListPagingSource(
    private val name: String? = null,
    private val apiService: ApiService
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val charactersResponse = apiService.getListCharacters(pageNumber, name)
            val characters = charactersResponse.results.map { character ->
                character.toCharacter()
            }
            var nextPage: Int? = null
            if (charactersResponse.info.next != null) {
                val uri = Uri.parse(charactersResponse.info.next)
                nextPage = uri.getQueryParameter("page")?.toInt()
            }
            LoadResult.Page(
                data = characters,
                nextKey = nextPage,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
