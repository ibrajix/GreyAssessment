package com.ibrajix.greyassessment.features.users.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.components.Loader
import com.ibrajix.greyassessment.components.RepositoryCardComponent
import com.ibrajix.greyassessment.data.response.UserDetailsResponse
import com.ibrajix.greyassessment.features.users.view_model.UsersDetailsViewModel
import com.ibrajix.greyassessment.ui.theme.Black
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.GreyShade4

@Composable
fun UserDetailsScreen(
    navController: NavController,
    viewModel: UsersDetailsViewModel = hiltViewModel()
){
    val isLoading by viewModel.isLoading.collectAsState()
    val userDetails by viewModel.userDetails.collectAsState()

    UserDetailsScreenContent(
        isLoading = isLoading,
        userDetails = userDetails,
        onEvent = { event->
            when(event){
                UserDetailsEvents.OnBackClicked -> {
                    navController.popBackStack()
                }
                UserDetailsEvents.OnBioClicked -> {

                }
                is UserDetailsEvents.OnRepoClicked -> {

                }
            }
        }
    )
}

@Composable
fun UserDetailsScreenContent(
    isLoading: Boolean,
    userDetails: UserDetailsResponse,
    onEvent: (UserDetailsEvents) -> Unit
){

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    GreyAssessmentTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 30.dp
                )
        ){
            if(isLoading){
                Loader(modifier = Modifier.align(Alignment.Center))
            }
            else{
                Column{

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .clickable{
                                    onEvent(UserDetailsEvents.OnBackClicked)
                                },
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "User Details",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row {
                        AsyncImage(
                            model = userDetails.avatarUrl,
                            contentDescription = "User profile image",
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Column {
                            Text(
                                text = userDetails.login ?: "",
                                fontWeight = FontWeight.W600,
                                lineHeight = TextUnit(16.3f, TextUnitType.Sp),
                            )
                            Text(
                                text = userDetails.name ?: "",
                                fontWeight = FontWeight.W500,
                                fontSize = 14.sp,
                                lineHeight = TextUnit(16.3f, TextUnitType.Sp),
                            )

                        }
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = userDetails.bio ?: "",
                        fontWeight = FontWeight.W600,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.location), contentDescription = "")
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = userDetails.location ?: "",
                            fontWeight = FontWeight.W600,
                            fontSize = 10.sp,
                            color = Black.copy(alpha = 0.5F)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Image(painter = painterResource(id = R.drawable.link), contentDescription = "")
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = userDetails.blog ?: "",
                            fontWeight = FontWeight.W600,
                            fontSize = 10.sp,
                            textDecoration = TextDecoration.Underline,
                            color = Black
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = R.drawable.people), contentDescription = "")
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = "${userDetails.followers} followers .",
                            fontWeight = FontWeight.W600,
                            fontSize = 10.sp,
                            color = Black.copy(alpha = 0.5F)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = "${userDetails.following} following",
                            fontWeight = FontWeight.W600,
                            fontSize = 10.sp,
                            color = Black.copy(alpha = 0.5F)
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        backgroundColor = Color.Transparent,
                        indicator = { tabPositions ->
                            Row(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[0]),
                                horizontalArrangement = Arrangement.Start,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(2.dp)
                                        .width(120.dp)
                                        .background(color = Black)
                                )
                            }
                        }
                    ) {
                        Tab(
                            selected = selectedTabIndex == 0,
                            onClick = { selectedTabIndex = 0 },
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .align(Alignment.Start)
                            ) {
                                Text(text = "Repositories", fontWeight = FontWeight.W600, fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(2.dp))
                                Badge(
                                    backgroundColor = GreyShade4,
                                    contentColor = Black,
                                ) {
                                    Text(
                                        text = userDetails.publicRepos.toString(),
                                        fontSize = 8.sp,
                                        modifier = Modifier
                                            .padding(horizontal = 2.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(10) { repository ->
                            RepositoryCardComponent(
                                description = "Complete framework to simplify the implementation of music commands using discord.js",
                                imageUrl = "",
                                language = "Vue",
                                numberOfStars = "10",
                                onClickCard = {},
                                repoName = "Aster/Discord-player",
                                tags = listOf(""),
                                isUserRepository = true
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class UserDetailsEvents {
    object OnBackClicked : UserDetailsEvents()
    object OnBioClicked : UserDetailsEvents()
    data class OnRepoClicked(val repoUrl: String) : UserDetailsEvents()
}

@Preview(showBackground = true)
@Composable
fun UserDetailsScreenPreview(){
    GreyAssessmentTheme {
        UserDetailsScreenContent(
            isLoading = false,
            userDetails = UserDetailsResponse(),
            onEvent = {}
        )
    }
}