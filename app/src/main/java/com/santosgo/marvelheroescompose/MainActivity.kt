package com.santosgo.marvelheroescompose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.MarvelHeroesComposeTheme
import com.santosgo.marvelheroescompose.ui.screens.FavListCompactScreen
import com.santosgo.marvelheroescompose.ui.screens.FavListMedExpScreen
import com.santosgo.marvelheroescompose.ui.screens.ProfileCompactScreen
import com.santosgo.marvelheroescompose.utils.getWindowSizeClass
import com.santosgo.mavelheroes.data.Datasource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MarvelHeroesComposeTheme(
                dynamicColor = false,
            ) {
                MarvelHeroesApp()
            }
        }
    }
}

@Composable
fun MarvelHeroesApp() {

    val heroes = Datasource.heroList()
    val windowSize = getWindowSizeClass(LocalContext.current as Activity)

    MarvelHeroesComposeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
//                    HeroListCompactScreen(heroes, Modifier.padding(innerPadding))
                    FavListCompactScreen(heroes, Modifier.padding(innerPadding))
                }
                else -> {
//                    HeroListMedExpScreen(heroes, Modifier.padding(innerPadding))
                    FavListMedExpScreen(heroes, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MarvelHeroesAppPreview() {
    MarvelHeroesComposeTheme {
        MarvelHeroesApp()
    }
}