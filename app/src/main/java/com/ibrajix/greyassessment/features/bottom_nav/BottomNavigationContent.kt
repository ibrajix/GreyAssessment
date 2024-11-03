package com.ibrajix.greyassessment.features.bottom_nav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ibrajix.greyassessment.MainViewModel
import com.ibrajix.greyassessment.components.BottomNavigationComponent
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

@Composable
fun BottomNavigationContent(
    mainNavController: NavHostController,
    mainViewModel: MainViewModel
) {
    val bottomBarNavController = rememberNavController()
    val items = mainViewModel.tabs.collectAsState()

    LaunchedEffect(Unit) {
        println("Items in tabs: ${items.value}")
    }

    LaunchedEffect(Unit) {
        mainViewModel.init()
    }

    LaunchedEffect(Unit) {
        bottomBarNavController.enableOnBackPressed(true)
    }

    LaunchedEffect(Unit) {
        mainViewModel.actionState.collect { action ->
            when (action) {
                is MainViewModel.ActionState.NavToTab -> {
                    bottomBarNavController.navigate("${action.tab}?subTab=${action.subTab}")
                }
            }
        }
    }

    GreyAssessmentTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation{
                    val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    BottomNavigationComponent(
                        items = items.value,
                        currentDestination = currentDestination,
                        bottomBarNavController = bottomBarNavController,
                        onItemClicked = {}
                    )
                }
            }
        ) { innerPadding ->
            BottomNavigationGraph(
                onBottomNavComposed = {
                    mainViewModel.onBottomNavComposed()
                },
                innerPadding = innerPadding,
                bottomBarNavController = bottomBarNavController,
                mainNavController = mainNavController
            )
        }
    }
}
