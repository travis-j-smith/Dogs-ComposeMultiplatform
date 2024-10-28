package com.iamtravisjsmith.dogs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.iamtravisjsmith.dogs.dogdetails.DogDetailsScreen
import com.iamtravisjsmith.dogs.doggrid.DogGridScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App(
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = DogGrid,
            ) {
                composable<DogGrid> {
                    DogGridScreen(
                        onDogClicked = { imageUrl ->
                            navController.navigate(DogDetails(imageUrl))
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                    )
                }
                composable<DogDetails> { backStackEntry ->
                    val dogDetails = backStackEntry.toRoute<DogDetails>()
                    DogDetailsScreen(
                        imageUrl = dogDetails.imageUrl,
                        navigateBack = { navController.popBackStack() },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this@composable,
                    )
                }
            }
        }
    }
}
