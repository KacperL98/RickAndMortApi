package com.example.rickandmortapicomp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortapicomp.presentation.characterList.CharactersList
import com.example.rickandmortapicomp.presentation.charactersSearch.CharactersSearch
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharactersList.route
    ) {
        composable(route = Screen.CharactersList.route) {
            CharactersList { navController.navigate(Screen.SearchScreen.route) }
        }
        composable(route = Screen.SearchScreen.route) {

            CharactersSearch {
                navController.navigateUp()
            }
        }

    }
}