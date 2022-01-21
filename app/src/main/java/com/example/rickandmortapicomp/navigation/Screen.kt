package com.example.rickandmortapicomp.navigation

sealed class Screen(val route: String) {

    object CharactersList : Screen("coin_list_screen")
    object SearchScreen: Screen("search_screen")

}