package com.ibrajix.greyassessment.features.users.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.greyassessment.data.repo.DataSource
import com.ibrajix.greyassessment.data.response.NetworkResponse
import com.ibrajix.greyassessment.data.response.User
import com.ibrajix.greyassessment.room.entity.UserEntity
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
    val users = MutableStateFlow<List<UserEntity>?>(null)


    fun onSearchQueryChange(query: String){
        searchQuery.update {
            query
        }
    }

    fun getUsers(query: String, ) {
        isLoading.update { true }
        viewModelScope.launch {
            val usersList = dataSource.getUsers(
                q = query,
                page = 1,
                perPage = 10
            )
            isLoading.update { false }

            if (usersList.isNotEmpty()) {
                users.update { usersList }
            } else {
                actionState.emit(UsersActionState.ShowToast("No users available"))
            }
        }
    }


    sealed class UsersActionState {
        data class ShowToast(val message: String) : UsersActionState()
    }
}