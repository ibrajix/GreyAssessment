package com.ibrajix.greyassessment.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibrajix.greyassessment.features.users.view.UsersScreenContent
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme

@Composable
fun Loader(
    radius: Float = 24f,
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier.size(radius.dp),
        strokeWidth = (radius / 8).dp
    )
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenPreview() {
    GreyAssessmentTheme {
        Loader()
    }
}