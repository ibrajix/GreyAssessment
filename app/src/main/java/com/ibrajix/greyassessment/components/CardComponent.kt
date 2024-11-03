package com.ibrajix.greyassessment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.TealShade

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color,
    image: Int,
    title: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(0.4.dp))
            .clickable { onClick() }
            .background(backgroundColor)
            .padding(14.dp)
            .fillMaxWidth()
    ){
        Column(
           verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Icon",
            )
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
            )
        }
    }
}

@Preview
@Composable
private fun GreyCardComponentPreview(){
    GreyAssessmentTheme {
        CardComponent(
            onClick = {},
            backgroundColor = TealShade,
            image = R.drawable.user_teal,
            title = "Users"
        )
    }
}