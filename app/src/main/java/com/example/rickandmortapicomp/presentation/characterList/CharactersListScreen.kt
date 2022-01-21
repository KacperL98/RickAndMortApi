package com.example.rickandmortapicomp.presentation.characterList

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortapicomp.presentation.characterList.components.CharactersListColumn

@ExperimentalAnimationApi
@Composable
fun CharactersList(searchScreen: () -> Unit) {

    val viewModel: CharactersListViewModel = hiltViewModel()
    val state = viewModel.characterListState.value

    val characters = state.dataList?.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "Rick And Morty")},
            actions = {
                IconButton(onClick = { searchScreen()  }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null )

                }
            })


        }
    ) {

        if (state.errorMessage.isNotEmpty()) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.errorMessage)
            }
        }

        characters?.let { items ->
            CharactersListColumn(items = items)

        }
    }
}
