package com.ibrajix.greyassessment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.greyassessment.navigation.BottomNavDestinations
import com.ibrajix.greyassessment.navigation.MainNavDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val tabs = MutableStateFlow(listOf<BottomNavTab>())
    private val selectedTab get() = savedStateHandle.get<String>(MainNavDestinations.tab)
    private val selectedSubTab get() = savedStateHandle.get<String>(MainNavDestinations.subTab)

    val actionState = MutableSharedFlow<ActionState>()

    fun init() {
        setUpBottomNav()
    }


    private fun setUpBottomNav(){
        viewModelScope.launch {
            mutableListOf(
                BottomNavTab("Home", BottomNavDestinations.home, R.drawable.home_outlined, R.drawable.home_filled),
                BottomNavTab("Repositories", BottomNavDestinations.repositories, R.drawable.search_outlined, R.drawable.search_filled),
                BottomNavTab("Users", BottomNavDestinations.users
                    , R.drawable.user_outlined, R.drawable.user_filled),
            )
        }
    }


    fun onBottomNavComposed() {
        viewModelScope.launch {
            selectedTab?.let {
                actionState.emit(
                    ActionState.NavToTab(
                        tab = it,
                        subTab = selectedSubTab
                    )
                )
                savedStateHandle.set(MainNavDestinations.tab, null)
                savedStateHandle.set(MainNavDestinations.subTab, null)
            }
        }
    }
    sealed class ActionState {
        data class NavToTab(val tab: String, val subTab: String?) : ActionState()
    }

}

data class BottomNavTab (
    val title: String,
    val route: String,
    val selectedIconRes: Int,
    val unselectedIconRes: Int
)