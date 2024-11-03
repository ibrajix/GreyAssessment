package com.ibrajix.greyassessment.features.repositories.view

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.components.EmptyStateComponent
import com.ibrajix.greyassessment.components.Loader
import com.ibrajix.greyassessment.components.RepositoryCardComponent
import com.ibrajix.greyassessment.components.SearchComponent
import com.ibrajix.greyassessment.components.UsersComponent
import com.ibrajix.greyassessment.data.response.RepositoryResponse
import com.ibrajix.greyassessment.data.response.User
import com.ibrajix.greyassessment.data.response.UserRepositoryResponse
import com.ibrajix.greyassessment.features.home.view_model.HomeViewModel
import com.ibrajix.greyassessment.features.repositories.view_model.RepositoriesViewModel
import com.ibrajix.greyassessment.features.users.view.UsersScreenEvents
import com.ibrajix.greyassessment.features.users.view_model.UsersViewModel
import com.ibrajix.greyassessment.navigation.UsersDestinations.userDetails
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import java.time.Duration
import java.time.Instant

@Composable
fun RepositoriesScreen(
    navController: NavHostController,
    viewModel: RepositoriesViewModel = hiltViewModel(),
) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val repositories by viewModel.repositories.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.actionState.collect { action->
            when(action){
                is RepositoriesViewModel.RepositoriesActionState.ShowToast -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    RepositoriesContent(
        searchQuery = searchQuery,
        isLoading = isLoading,
        repositories = repositories,
        onEvent = { event->
            when(event){
                RepositoriesScreenEvents.OnClickSearch -> {
                    viewModel.getRepos(searchQuery)
                }
                is  RepositoriesScreenEvents.OnQueryChange -> {
                    viewModel.onSearchQueryChange(event.query)
                }

                is RepositoriesScreenEvents.OnClickCard -> {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.url))
                    context.startActivity(intent)
                }
            }
        }
    )

}

@Composable
fun RepositoriesContent(
    onEvent: (RepositoriesScreenEvents) -> Unit,
    searchQuery: String,
    isLoading: Boolean,
    repositories: List<UserRepositoryResponse>?
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
                    text = "Repositories",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                )
                Spacer(modifier = Modifier.size(8.dp))
                SearchComponent(
                    query = searchQuery,
                    onQueryChange = { newQuery -> onEvent(RepositoriesScreenEvents.OnQueryChange(newQuery)) },
                    searchHint = "Search for repositories...",
                    onSearchClick = { onEvent(RepositoriesScreenEvents.OnClickSearch)},
                    isLoading = isLoading
                )
                Spacer(modifier = Modifier.size(20.dp))
                when {
                    isLoading -> {
                        Loader(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    repositories == null -> {
                        EmptyStateComponent(
                            image = R.drawable.empty_state,
                            text = "Search Github for repositories, issues and pull requests!"
                        )
                    }
                    repositories.isEmpty() -> {
                        EmptyStateComponent(
                            image = R.drawable.empty_state,
                            text = "We’ve searched the ends of the earth and we’ve not found this repository, please try again"
                        )
                    }
                    else -> {
                        LazyColumn {
                            items(repositories) { repository ->
                                RepositoryCardComponent(
                                    description = repository.description ?: "",
                                    imageUrl = repository.owner.avatarUrl ?: "",
                                    language = repository.language ?: "",
                                    numberOfStars = repository.stargazersCount.toString(),
                                    onClickCard = {
                                        onEvent(RepositoriesScreenEvents.OnClickCard(repository.htmlUrl?: ""))
                                    },
                                    repoName = repository.fullName ?: "",
                                    tags = repository.topics ?: emptyList(),
                                    visibility = repository.visibility?.replaceFirstChar { it.uppercaseChar() } ?: "",
                                    isUserRepository = false,
                                    updated =  "Updated ${Duration.between(Instant.parse(repository.updatedAt), Instant.now()).toDays()} days ago"
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                            }
                        }

                    }
                }
            }
        }
    }
}

sealed class RepositoriesScreenEvents {
    data class OnQueryChange(val query: String) : RepositoriesScreenEvents()
    object OnClickSearch : RepositoriesScreenEvents()
    data class OnClickCard(val url: String) : RepositoriesScreenEvents()
}

@Preview(showBackground = true)
@Composable
private fun RepositoriesPreview() {
    GreyAssessmentTheme {
        RepositoriesContent(
            onEvent = {},
            searchQuery = "",
            isLoading = false,
            repositories = listOf()
        )
    }
}