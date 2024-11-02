package com.ibrajix.greyassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrajix.greyassessment.features.bottom_nav.BottomNavigationContent
import com.ibrajix.greyassessment.navigation.MainNavDestinations.main
import com.ibrajix.greyassessment.navigation.MainNavDestinations.subTab
import com.ibrajix.greyassessment.navigation.MainNavDestinations.tab
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "$main/{$tab}?subTab={$subTab}",
                route = "/"
            ){
                composable(
                    route = "$main/{$tab}?subTab={$subTab}",
                    arguments = listOf(navArgument(subTab) {
                        nullable = true
                        defaultValue = null
                    }
                    )) {
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    BottomNavigationContent(
                        mainNavController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }

        }
    }
}