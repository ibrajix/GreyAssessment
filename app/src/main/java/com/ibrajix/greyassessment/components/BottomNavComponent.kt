package com.ibrajix.greyassessment.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.BottomNavTab
import com.ibrajix.greyassessment.navigation.BottomNavDestinations

@Composable
fun RowScope.BottomNavigationComponent(
    items: List<BottomNavTab>,
    currentDestination: NavDestination?,
    bottomBarNavController: NavHostController,
    onItemClicked: (BottomNavTab) -> Unit
) {
    items.forEachIndexed { index, screen ->

        val isSelected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = if (isSelected) screen.selectedIconRes else screen.unselectedIconRes),
                    modifier = Modifier.size(26.dp),
                    contentDescription = null
                )
            },
            label = {
                Text(screen.title, maxLines = 1)
            },
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.primaryVariant,
            selected = currentDestination?.hierarchy?.any {
                it.route?.startsWith(screen.route) == true
            } == true,
            enabled = true,
            onClick = {
                bottomBarNavController.navigate(screen.route) {

                    popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    if (screen.route != BottomNavDestinations.home) {
                        restoreState = true
                    }
                }
                onItemClicked(screen)
            }
        )
    }
}