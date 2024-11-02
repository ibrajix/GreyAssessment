package com.ibrajix.greyassessment.features.bottom_nav

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibrajix.greyassessment.features.home.view.HomeScreen
import com.ibrajix.greyassessment.features.repositories.view.RepositoriesScreen
import com.ibrajix.greyassessment.features.users.view.UsersScreen
import com.ibrajix.greyassessment.navigation.BottomNavDestinations

@Composable
fun BottomNavigationGraph(
    onBottomNavComposed: () -> Unit,
    innerPadding: PaddingValues,
    bottomBarNavController: NavHostController,
    mainNavController: NavHostController,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        onBottomNavComposed()
    }

    NavHost(
        bottomBarNavController,
        startDestination = BottomNavDestinations.home,
        modifier = Modifier.padding(innerPadding)
    ){
        composable(BottomNavDestinations.home) {
            HomeScreen(
                navController = mainNavController
            )
        }
        composable(BottomNavDestinations.repositories) {
            RepositoriesScreen(
                navController = mainNavController
            )
        }
        composable(BottomNavDestinations.repositories) {
            RepositoriesScreen(
                navController = mainNavController
            )
        }

        composable(BottomNavDestinations.users) {
            UsersScreen(
                navController = mainNavController
            )
        }


    }

}