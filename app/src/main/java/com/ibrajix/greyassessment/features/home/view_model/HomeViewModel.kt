package com.ibrajix.greyassessment.features.home.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    val isDarkMode = MutableStateFlow(false)

    fun toggleTheme() {
        isDarkMode.update { value->
            !value
        }
    }
}