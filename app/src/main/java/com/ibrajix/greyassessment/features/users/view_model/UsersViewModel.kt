package com.ibrajix.greyassessment.features.users.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.greyassessment.data.repo.DataSource
import com.ibrajix.greyassessment.data.response.NetworkResponse
import com.ibrajix.greyassessment.data.response.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val dataSource: DataSource) : ViewModel() {

    val actionState = MutableSharedFlow<UsersActionState>()

    val searchQuery = MutableStateFlow("")
    val isLoading = MutableStateFlow(false)
    val users = MutableStateFlow<List<User>?>(null)


    fun onSearchQueryChange(query: String){
        searchQuery.update {
            query
        }
    }

    fun getUsers(query: String, ) {
        isLoading.update { true }
        viewModelScope.launch {
            val response = dataSource.getUsers(
                q = query,
                page = 1,
                perPage = 10
            )
            isLoading.update { false }
            when(response){
                is NetworkResponse.Failure ->{
                    actionState.emit(UsersActionState.ShowToast(response.error.message))
                }
                is NetworkResponse.Success -> {
                   users.update {
                       response.body.items
                   }
                }
            }

        }
    }


    sealed class UsersActionState {
        data class ShowToast(val message: String) : UsersActionState()
    }
}