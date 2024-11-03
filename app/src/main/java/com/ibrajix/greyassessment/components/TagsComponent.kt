package com.ibrajix.greyassessment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.Teal
import com.ibrajix.greyassessment.ui.theme.TealShade
import com.ibrajix.greyassessment.ui.theme.TealShade2

@Composable
fun TagsComponent(
    backgroundColor: Color = TealShade2,
    textTitle: String
){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(vertical = 6.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text =  textTitle,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.W600,
            fontSize = 10.sp,
            color = Teal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TagsComponentPreview(){
    GreyAssessmentTheme {
        TagsComponent(
            textTitle = "Design System"
        )
    }
}