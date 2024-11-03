package com.ibrajix.greyassessment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme


@Composable
fun EmptyStateComponent(
    image: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(14.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Icon",
            )
            Text(
                textAlign = TextAlign.Center,
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EmptyStateComponentPreview(){
    GreyAssessmentTheme {
        EmptyStateComponent(
            text = "We’ve searched the ends of the earth and we’ve not found this user, please try again",
            image = R.drawable.empty_state
        )
    }
}
