package com.ibrajix.greyassessment.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.ibrajix.greyassessment.BottomNavTab


@Composable
fun BottomNavigationComponent(
    items: List<BottomNavTab>,
    currentDestination: NavDestination?,
    bottomBarNavController: NavHostController,
    onItemClicked: (BottomNavTab) -> Unit
) {
    NavigationBar {
        items.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
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
                selected = isSelected,
                onClick = {
                    bottomBarNavController.navigate(screen.route) {
                        popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    onItemClicked(screen)
                }
            )
        }
    }
}
