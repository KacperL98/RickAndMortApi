package com.example.rickandmortapicomp.data.remote.models.characters

import kotlinx.serialization.SerialName

data class Character(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("origin")
    val origin: String,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("gender")
    val gender: String,
)