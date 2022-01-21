package com.example.rickandmortapicomp.data.remote.models.characters.mappers

import com.example.rickandmortapicomp.data.remote.models.characters.Character
import com.example.rickandmortapicomp.data.remote.models.characters.ResultDto

fun ResultDto.toCharacter(): Character {

    return Character(
        id = id,
        name = name,
        image = image,
        gender = gender,
        status = status,
        origin = origin.name,
        species = species

    )
}

