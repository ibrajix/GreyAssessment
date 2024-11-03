package com.ibrajix.greyassessment.features.repositories.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.greyassessment.data.repo.DataSource
import com.ibrajix.greyassessment.data.response.NetworkResponse
import com.ibrajix.greyassessment.data.response.User
import com.ibrajix.greyassessment.data.response.UserRepositoryResponse
import com.ibrajix.greyassessment.features.users.view_model.UsersViewModel.UsersActionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private  val dataSource: DataSource
) : ViewModel() {

    val actionState = MutableSharedFlow<RepositoriesActionState>()

    val searchQuery = MutableStateFlow("")
    val isLoading = MutableStateFlow(false)
    val repositories = MutableStateFlow<List<UserRepositoryResponse>?>(null)

    fun onSearchQueryChange(query: String){
        searchQuery.update {
            query
        }
    }

    fun getRepos(query: String, ) {
        isLoading.update { true }
        viewModelScope.launch {
            val response = dataSource.getRepositories(
                q = query,
                page = 1,
                perPage = 10
            )
            isLoading.update { false }
            when(response){
                is NetworkResponse.Failure ->{
                    actionState.emit(RepositoriesActionState.ShowToast(response.error.message))
                }
                is NetworkResponse.Success -> {
                    repositories.update {
                        response.body.items
                    }
                }
            }

        }
    }

    sealed class RepositoriesActionState {
        data class ShowToast(val message: String) : RepositoriesActionState()
    }
}