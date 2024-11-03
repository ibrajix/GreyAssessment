package com.ibrajix.greyassessment.features.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.components.CardComponent
import com.ibrajix.greyassessment.features.home.view_model.HomeViewModel
import com.ibrajix.greyassessment.navigation.BottomNavDestinations.repositories
import com.ibrajix.greyassessment.navigation.BottomNavDestinations.users
import com.ibrajix.greyassessment.navigation.MainNavDestinations.main
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.PinkShade
import com.ibrajix.greyassessment.ui.theme.TealShade


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    HomeScreenContent(
        onEvent = { action->
            when(action){
                HomeScreenEvents.OnClickUsers -> {
                    navController.navigate("$main/$users")
                }
                HomeScreenEvents.OnClickRepositories -> {
                    navController.navigate("$main/$repositories")
                }
            }
        }
    )
}

@Composable
fun HomeScreenContent(
    onEvent: (HomeScreenEvents) -> Unit,
){
    GreyAssessmentTheme{
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 40.dp
                )
        ){
            Column {
                Text(
                    text = "Home",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                )
                Spacer(modifier = Modifier.size(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CardComponent(
                        modifier = Modifier.weight(1f),
                        backgroundColor = TealShade,
                        image = R.drawable.user_teal,
                        title = "Users",
                        onClick = {
                            onEvent(HomeScreenEvents.OnClickUsers)
                        }
                    )
                    CardComponent(
                        modifier = Modifier.weight(1f),
                        backgroundColor = PinkShade,
                        image = R.drawable.code_folder,
                        title = "Repositories",
                        onClick = {
                            onEvent(HomeScreenEvents.OnClickRepositories)
                        },
                    )
                }
            }
        }
    }
}

sealed class HomeScreenEvents {
    object OnClickUsers : HomeScreenEvents()
    object OnClickRepositories : HomeScreenEvents()
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    GreyAssessmentTheme {
        HomeScreenContent(
            onEvent = {}
        )
    }
}