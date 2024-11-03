package com.ibrajix.greyassessment.features.users.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.components.EmptyStateComponent
import com.ibrajix.greyassessment.components.SearchComponent
import com.ibrajix.greyassessment.features.users.view_model.UsersViewModel
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

@Composable
fun UsersScreen (
    navController: NavHostController,
    viewModel: UsersViewModel = hiltViewModel(),
) {
    UsersScreenContent()
}

@Composable
fun UsersScreenContent(){
    GreyAssessmentTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 40.dp
                )
        ){
            Column{
                Text(
                    text = "Users",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                )
                Spacer(modifier = Modifier.size(8.dp))
                SearchComponent(query = "", onQueryChange = {}, searchHint = "Search for users",  onSearchClick = {})
                Spacer(modifier = Modifier.size(20.dp))
                EmptyStateComponent(
                    image = R.drawable.empty_state,
                    text = "Search Github Users..."
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