package com.ibrajix.greyassessment.features.users.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.greyassessment.data.repo.DataSource
import com.ibrajix.greyassessment.data.response.NetworkResponse
import com.ibrajix.greyassessment.data.response.UserDetailsResponse
import com.ibrajix.greyassessment.features.users.view_model.UsersViewModel.UsersActionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class UsersDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private  val dataSource: DataSource
) : ViewModel() {
    private val login = requireNotNull(savedStateHandle.get<String>("login"))
    val isLoading = MutableStateFlow(false)
    val userDetails = MutableStateFlow(UserDetailsResponse())

    init {
        getUserDetails(login)
    }

    fun getUserDetails(login: String) {
        isLoading.update { true }
        viewModelScope.launch {
            val response = dataSource.getUserDetails(login)
            isLoading.update { false }
            when(response){
                is NetworkResponse.Failure ->{
                    UsersActionState.ShowToast(response.error.message)
                }
                is NetworkResponse.Success -> {
                    userDetails.update {
                        response.body
                    }
                }
            }

        }
    }

}