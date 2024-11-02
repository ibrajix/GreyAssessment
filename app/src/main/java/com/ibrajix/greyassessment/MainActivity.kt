package com.ibrajix.greyassessment

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ibrajix.greyassessment.features.bottom_nav.BottomNavigationContent
import com.ibrajix.greyassessment.navigation.MainNavDestinations.main
import com.ibrajix.greyassessment.navigation.MainNavDestinations.subTab
import com.ibrajix.greyassessment.navigation.MainNavDestinations.tab
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
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

            GreyAssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreyAssessmentTheme {
        Greeting("Android")
    }
}