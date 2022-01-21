package com.example.rickandmortapicomp.presentation.characterList.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.coil.CoilImage

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.example.rickandmortapicomp.data.remote.models.characters.Character
import com.skydoves.landscapist.CircularReveal

@ExperimentalAnimationApi
@Composable
fun CharacterUI(character: Character) {
    AnimatedVisibility(visible = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp),
            elevation = 8.dp,
        ) {
            Box(modifier = Modifier.size(8.dp)) {
                ImageCard(
                    imageLink = character.image,
                    modifier = Modifier.fillMaxWidth(),
                )
                CharacterInfo(
                    character = character, modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                .2F to Color.Transparent,
                                .5F to Color.Black,
                                .5F to Color.Black
                            )
                        )

                )
            }
        }
    }
}


@Composable
fun ImageCard(imageLink: String, modifier: Modifier) {
    CoilImage(
        imageRequest =
        ImageRequest
            .Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .build(),
        alignment = Alignment.Center,
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        },
        circularReveal = CircularReveal(
            duration = 300,
        ),
        modifier = modifier
    )
}

@Composable
fun CharacterInfo(character: Character, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = character.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
    }
}
