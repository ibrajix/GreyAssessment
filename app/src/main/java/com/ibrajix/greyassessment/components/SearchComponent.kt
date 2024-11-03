package com.ibrajix.greyassessment.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrajix.greyassessment.R
import com.ibrajix.greyassessment.ui.theme.BlackShade
import com.ibrajix.greyassessment.ui.theme.GreyAssessmentTheme
import com.ibrajix.greyassessment.ui.theme.GreyShade
import com.ibrajix.greyassessment.ui.theme.White


@Composable
fun SearchComponent(
    query: String,
    onQueryChange: (String) -> Unit,
    searchHint: String,
    isLoading: Boolean,
    onSearchClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    searchHint,
                    color = GreyShade,
                    fontSize = 12.sp
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
                focusManager.clearFocus()
            }
        ),

        trailingIcon = {
           if (isLoading) Loader()
            else Button(
                onClick = {
                    focusManager.clearFocus()
                    onSearchClick()
                },
                modifier = Modifier
                    .padding(end = 6.dp)
                    .width(84.dp) ,
                colors = ButtonDefaults.buttonColors(backgroundColor = BlackShade),
            ) {
                Text(
                    "Search",
                    color = White,
                    fontSize = 10.sp
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlackShade,
            unfocusedBorderColor = BlackShade
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchComponentPreview() {
    GreyAssessmentTheme {
        SearchComponent(
            query = "",
            onQueryChange = {},
            searchHint = "Search for users...",
            isLoading = true,
            onSearchClick = {}
        )
    }
}

