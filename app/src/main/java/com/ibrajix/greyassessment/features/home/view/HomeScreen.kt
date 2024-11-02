package com.ibrajix.greyassessment.features.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.features.home.view_model.HomeViewModel
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

}

@Composable
fun HomeScreenContent(){
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
private fun HomeScreenPreview() {
    GreyAssessmentTheme {
        HomeScreenContent()
    }
}