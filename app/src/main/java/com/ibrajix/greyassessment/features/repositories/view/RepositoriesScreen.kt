package com.ibrajix.greyassessment.features.repositories.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.features.home.view_model.HomeViewModel
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

@Composable
fun RepositoriesScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    RepositoriesContent()

}

@Composable
fun RepositoriesContent(){
    GreyAssessmentTheme {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ){
            Column {
                Text(
                    text = "Repositories",

                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoriesPreview() {
    GreyAssessmentTheme {
        RepositoriesContent()
    }
}