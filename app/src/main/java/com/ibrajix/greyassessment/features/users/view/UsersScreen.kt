package com.ibrajix.greyassessment.features.users.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.components.EmptyStateComponent
import com.ibrajix.greyassessment.components.Loader
import com.ibrajix.greyassessment.components.SearchComponent
import com.ibrajix.greyassessment.components.UsersComponent
import com.ibrajix.greyassessment.data.response.User
import com.ibrajix.greyassessment.features.home.view.HomeScreenEvents
import com.ibrajix.greyassessment.features.users.view_model.UsersViewModel
import com.ibrajix.greyassessment.navigation.BottomNavDestinations.users
import com.ibrajix.greyassessment.navigation.UsersDestinations.userDetails
import com.ibrajix.greyassessment.room.entity.UserEntity
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

@Composable
fun UsersScreen (
    navController: NavHostController,
    viewModel: UsersViewModel = hiltViewModel(),
) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val users by viewModel.users.collectAsState()
    val recentUsers by viewModel.recentUsersSearch.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.actionState.collect { action->
            when(action){
                is UsersViewModel.UsersActionState.ShowToast -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    UsersScreenContent(
        searchQuery = searchQuery,
        isLoading = isLoading,
        users = users,
        recentUsers = recentUsers,
        onEvent = { event->
            when(event){
                UsersScreenEvents.OnClickSearch -> {
                    viewModel.getUsers(searchQuery)
                }
                is UsersScreenEvents.OnQueryChange -> {
                    viewModel.onSearchQueryChange(event.query)
                }

                is UsersScreenEvents.OnClickCard -> {
                    navController.navigate("${userDetails}/${event.login}")
                }
            }
        }
    )
}

@Composable
fun UsersScreenContent(
    onEvent: (UsersScreenEvents) -> Unit,
    searchQuery: String,
    isLoading: Boolean,
    users: List<UserEntity>?,
    recentUsers: List<UserEntity>?
){
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
                SearchComponent(
                    query = searchQuery,
                    onQueryChange = { newQuery -> onEvent(UsersScreenEvents.OnQueryChange(newQuery)) },
                    searchHint = "Search for users",
                    onSearchClick = { onEvent(UsersScreenEvents.OnClickSearch)},
                    isLoading = isLoading
                )
                Spacer(modifier = Modifier.size(20.dp))

                if (recentUsers?.isNotEmpty() == true && users == null) {
                    Text(text = "Recent Searches Result", style = TextStyle(fontSize = 14.sp))
                    Spacer(modifier = Modifier.size(10.dp))
                    LazyColumn {
                        items(recentUsers) { user ->
                            UsersComponent(
                                onClickCard = {
                                    onEvent(UsersScreenEvents.OnClickCard(user.login))
                                },
                                fullName = user.login,
                                userName = user.login,
                                bio = "Random bio",
                                location = "Random location",
                                email = "Random email",
                                imageUrl = user.avatarUrl
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                }
                else if (!users.isNullOrEmpty()) {
                    LazyColumn {
                        items(users) { user ->
                            UsersComponent(
                                onClickCard = {
                                    onEvent(UsersScreenEvents.OnClickCard(user.login))
                                },
                                fullName = user.login,
                                userName = user.login,
                                bio = "Random bio",
                                location = "Random location",
                                email = "Random email",
                                imageUrl = user.avatarUrl
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
                } else if (isLoading) {
                    Loader(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    EmptyStateComponent(
                        image = R.drawable.empty_state,
                        text = "Search Github Users..."
                    )
                }

            }
        }
    }
}

sealed class UsersScreenEvents {
    data class OnQueryChange(val query: String) : UsersScreenEvents()
    object OnClickSearch : UsersScreenEvents()
    data class OnClickCard(val login: String) : UsersScreenEvents()
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenPreview() {
    GreyAssessmentTheme {
        UsersScreenContent(
            onEvent = {},
            searchQuery = "",
            isLoading = false,
            users = listOf(),
            recentUsers = listOf()
        )
    }
}