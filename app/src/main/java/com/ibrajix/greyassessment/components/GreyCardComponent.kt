package com.ibrajix.greyassessment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.GreyTealShade

@Composable
fun GreyCardComponent(
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(0.4.dp))
            .clickable { onClick() }
            .background(backgroundColor)
            .padding(18.dp)
            .fillMaxWidth()
    ){
        Column {

        }
    }
}

@Preview
@Composable
private fun GreyCardComponentPreview(){
    GreyAssessmentTheme {
        GreyCardComponent(
            onClick = {},
            backgroundColor = GreyTealShade
        )
    }
}