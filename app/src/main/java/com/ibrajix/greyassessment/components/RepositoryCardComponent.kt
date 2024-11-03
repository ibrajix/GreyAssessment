package com.ibrajix.greyassessment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.ui.theme.Black
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.GreyShade2

@Composable
fun RepositoryCardComponent(
    onClickCard: () -> Unit,
    imageUrl: String,
    repoName: String,
    description: String,
    numberOfStars: String,
    language: String,
    isUserRepository: Boolean = true,
    tags: List<String>
){
    Box(
        Modifier
            .fillMaxWidth()
            .border(1.dp, GreyShade2.copy(alpha = 0.6F), RoundedCornerShape(10.dp))
            .clickable { onClickCard() }
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box{
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if(!isUserRepository){
                            Image(painter = painterResource(id = R.drawable.user_teal), contentDescription = "")
                        }
                        Text(
                            text = repoName,
                            fontSize = 12.sp
                        )
                        if(isUserRepository){
                            Spacer(modifier = Modifier.size(9.dp))
                            Box(
                                modifier = Modifier
                                    .border(
                                        1.dp,
                                        GreyShade2.copy(alpha = 0.6F),
                                        RoundedCornerShape(10.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 1.dp)

                            ){
                                Text(
                                    text = "Public",
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
                Box{
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.star), contentDescription = "")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = numberOfStars,
                                fontSize = 10.sp
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(id = R.drawable.ellipse), contentDescription = "")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = language,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = description,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.size(18.dp))
            if(isUserRepository){
                Row {
                    Text(
                        text = "Forked from discordify",
                        fontSize = 10.sp,
                        color = Black.copy(alpha = 0.5F)
                    )
                    Spacer(
                        modifier = Modifier
                            .size(17.dp)
                    )
                    Text(
                        text = "Updated 4 days ago",
                        fontSize = 10.sp,
                        color = Black.copy(alpha = 0.5F)
                    )
                }
            }
            else{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    tags.take(3).forEach { title ->
                        TagsComponent(textTitle = title)
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryComponentPreview(){
    GreyAssessmentTheme {
        RepositoryCardComponent(
            imageUrl = "",
            repoName = "Vundere/Python",
            description = "These are random words that will be replaced in due time. Config files for my github profile",
            numberOfStars = "10",
            tags = listOf("Design System", "Component-misc", "Status-new"),
            language = "Python",
            onClickCard = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryComponentPreview2(){
    GreyAssessmentTheme {
        RepositoryCardComponent(
            imageUrl = "",
            repoName = "Vundere/Python",
            description = "These are random words that will be replaced in due time. Config files for my github profile",
            numberOfStars = "10",
            tags = listOf("Design System", "Component-misc", "Status-new"),
            language = "Python",
            isUserRepository = false,
            onClickCard = {}
        )
    }
}
