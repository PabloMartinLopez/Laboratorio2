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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.MarvelHeroesComposeTheme
import com.santosgo.marvelheroescompose.ui.screens.FavListCompactScreen
import com.santosgo.marvelheroescompose.ui.screens.FavListMedExpScreen
import com.santosgo.marvelheroescompose.ui.screens.HeroDetailCompactScreen
import com.santosgo.marvelheroescompose.ui.screens.HeroListCompactScreen
import com.santosgo.marvelheroescompose.ui.screens.HeroListMedExpScreen
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
    // Crear la lista de héroes y añadir duplicados para probar el desplazamiento
    val heroes = Datasource.getListXtimes(4)
    val windowSize = getWindowSizeClass(LocalContext.current as Activity)
    val navController = rememberNavController()

    MarvelHeroesComposeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { /* Aquí puedes agregar lógica dinámica para la top bar */ },
            bottomBar = { /* Aquí puedes agregar lógica dinámica para la bottom bar */ },
            floatingActionButton = {} // Lógica del FAB si aplica
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "hero_list",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("hero_list") {
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> { HeroListCompactScreen(heroes, navController, Modifier.padding(8.dp)) }
                        else -> { HeroListMedExpScreen(heroes, navController, Modifier.padding(8.dp)) }
                    }
                }
                composable("fav_list") {
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> { FavListCompactScreen(heroes, Modifier.padding(8.dp)) }
                        else -> { FavListMedExpScreen(heroes, Modifier.padding(8.dp)) }
                    }
                }
                composable("profile") {
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> { ProfileCompactScreen(Modifier.padding(8.dp)) }
                        else -> { ProfileCompactScreen(Modifier.padding(8.dp)) }
                    }
                }
                composable("hero_detail/{hero_name}") {
                    val heroName = it.arguments?.getString("hero_name") ?: "NoName"
                    when (windowSize) {
                        WindowWidthSizeClass.Compact -> { HeroDetailCompactScreen(heroName, navController, Modifier.padding(8.dp)) }
                        else -> { HeroDetailCompactScreen(heroName, navController, Modifier.padding(8.dp)) }
                    }
                }
            }
        }
    }
}
