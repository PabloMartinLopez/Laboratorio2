package com.santosgo.marvelheroescompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.santosgo.marvelheroescompose.R
import com.santosgo.marvelheroescompose.model.Hero
import com.santosgo.marvelheroescompose.ui.components.HeroCard
import com.santosgo.marvelheroescompose.ui.components.HeroCardLand
import com.santosgo.marvelheroescompose.ui.components.ImageComp
import com.santosgo.marvelheroescompose.ui.components.MedHeaderComp
import com.santosgo.marvelheroescompose.ui.components.StandardButtonComp
import com.santosgo.marvelheroescompose.ui.components.StandardTextComp
import com.santosgo.mavelheroes.data.Datasource

@Composable
fun HeroListCompactScreen(heroes: MutableList<Hero>, navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MedHeaderComp(title = stringResource(id = R.string.marvel_hero_list))
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(heroes) { hero ->
                HeroCard(hero) {
                    navController.navigate("hero_detail/${hero.name}")
                }
            }
        }
    }
}

@Composable
fun HeroListMedExpScreen(
    heroes: MutableList<Hero>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        //MedHeaderComp(title = "Pantalla media o grande")
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            items(heroes) { hero ->
                HeroCardLand(hero)
            }
        }
    }
}

@Composable
fun HeroDetailCompactScreen(
    hero_name: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val hero = Datasource.getHeroByName(hero_name)
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        hero?.let {
            ImageComp(
                drawable = Datasource.getDrawableIdByName(hero.photo),
                contentDesc = stringResource(R.string.hero_image_desc,hero.name),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.padding(16.dp).widthIn(200.dp, 300.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            StandardTextComp(
                text = hero.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StandardTextComp(text = stringResource(R.string.power, hero.power))
                StandardTextComp(text = stringResource(R.string.intelligence, hero.intelligence))
            }
            Spacer(modifier = Modifier.height(16.dp))
            StandardTextComp(
                text = hero.description,
                style = MaterialTheme.typography.bodyMedium
            )
        } ?: StandardTextComp(stringResource(R.string.hero_not_found), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))
//        StandardButtonComp(label = stringResource(R.string.back), onClick = { })
        StandardButtonComp(label = stringResource(R.string.back), onClick = { navController.navigateUp() })
    }
}