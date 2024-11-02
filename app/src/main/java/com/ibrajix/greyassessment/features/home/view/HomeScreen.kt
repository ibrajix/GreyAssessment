package com.ibrajix.greyassessment.features.home.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.features.home.view_model.HomeViewModel
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {


    val isDarkMode by viewModel.isDarkMode.collectAsState()

    HomeScreenContent(
        isDarkMode = isDarkMode,
        onEvent = { action->
            when(action){
                HomeScreenEvents.OnToggleTheme -> {
                    viewModel.toggleTheme()
                }
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    isDarkMode: Boolean,
    onEvent: (HomeScreenEvents) -> Unit,
){
    GreyAssessmentTheme(
        darkTheme = isDarkMode
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ){
            Column {
                Text(
                    text = "Home",
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = {
                        onEvent(HomeScreenEvents.OnToggleTheme)
                    }
                )
            }
        }
    }
}

sealed class HomeScreenEvents {
    object OnToggleTheme : HomeScreenEvents()
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    GreyAssessmentTheme {
        HomeScreenContent(
            isDarkMode = false,
            onEvent = {}
        )
    }
}