package com.example.rickandmortapicomp.presentation.charactersSearch

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortapicomp.presentation.characterList.components.CharactersListColumn
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@Composable
fun CharactersSearch(
    viewModel: CharacterSearchViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val state = viewModel.searchResult.value

    val characters = state.dataList?.collectAsLazyPagingItems()

    val searchString = viewModel.searchString.collectAsState().value
    LaunchedEffect(key1 = searchString) {
        if (searchString.length >= 3) {

            viewModel.searchCharacterbyName(searchString)
        }
    }
    Scaffold(
        topBar =
        {
            CustomSearchBar(
                value = searchString,
                placeholder = "Search Characters",
                navigateUp = navigateUp, onValueChange = { name ->
                    viewModel.searchCharacter(name)
                }
            )
        }
    ) {

        characters?.let { searchCharacters ->
            CharactersListColumn(items = searchCharacters)
        }
    }
}
