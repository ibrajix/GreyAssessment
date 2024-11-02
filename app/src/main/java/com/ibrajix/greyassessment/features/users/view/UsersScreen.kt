package com.ibrajix.greyassessment.features.users.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.features.home.view.HomeScreenContent
import com.ibrajix.greyassessment.features.home.view_model.HomeViewModel
import com.ibrajix.greyassessment.features.users.view_model.UsersViewModel
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

@Composable
fun UsersScreen (
    navController: NavHostController,
    viewModel: UsersViewModel = hiltViewModel(),
) {
}

@Composable
fun UsersScreenContent(){
    GreyAssessmentTheme {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ){
            Column {
                Text(
                    text = "Home",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenPreview() {
    GreyAssessmentTheme {
        UsersScreenContent()
    }
}