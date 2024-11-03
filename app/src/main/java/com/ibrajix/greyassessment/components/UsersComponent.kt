package com.ibrajix.greyassessment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.GreyShade2
import com.ibrajix.greyassessment.ui.theme.GreyShade3
import com.ibrajix.greyassessment.ui.theme.Teal

@Composable
fun UsersComponent(
   onClickCard : () -> Unit,
   fullName: String,
   userName: String,
   bio: String,
   location: String,
   email: String
){
    Box(
        Modifier
            .fillMaxWidth()
            .border(1.dp, GreyShade2.copy(alpha = 0.6F), RoundedCornerShape(10.dp))
            .clickable { onClickCard() }
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Image(painter = painterResource(id = R.drawable.user_teal), contentDescription = "")
                Spacer(modifier = Modifier.size(3.dp))
                Column {
                    Text(
                        text = fullName,
                        fontSize = 12.sp,
                        color = Teal,
                        lineHeight = TextUnit(16.3f, TextUnitType.Sp),
                    )
                    Text(
                        text = userName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = TextUnit(13.6f, TextUnitType.Sp),
                    )
                    Text(
                        text = bio,
                        fontSize = 10.sp,
                        lineHeight = TextUnit(20f, TextUnitType.Sp),
                    )
                    Row {
                        Text(
                            text = location,
                            fontSize = 8.sp,
                            color = GreyShade3,
                            lineHeight = TextUnit(10.9f, TextUnitType.Sp),
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = email,
                            fontSize = 8.sp,
                            color = GreyShade3,
                            fontWeight = FontWeight.W500,
                            lineHeight = TextUnit(10.9f, TextUnitType.Sp),
                        )

                    }
                }

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UsersComponentPreview(){
    GreyAssessmentTheme {
        UsersComponent(
            fullName = "Paige Brown",
            userName = "PaigeKelly23",
            bio = "This is a random bio, which will be replace with actual content",
            location = "Lagos, Nigeria",
            email = "momoko@gmail.com",
            onClickCard = {}
        )
    }
}