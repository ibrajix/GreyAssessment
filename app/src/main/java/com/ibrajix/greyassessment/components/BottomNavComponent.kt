package com.ibrajix.greyassessment.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
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

@Composable
fun BottomNavigationComponent(
    items: List<BottomNavTab>,
    currentDestination: NavDestination?,
    bottomBarNavController: NavHostController,
    onItemClicked: (BottomNavTab) -> Unit
) {
    val colors = MaterialTheme.colors

    BottomNavigation(
        backgroundColor = colors.background
    ) {
        items.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (isSelected) screen.selectedIconRes else screen.unselectedIconRes),
                        modifier = Modifier.size(26.dp),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        screen.title,
                        maxLines = 1,
                    )
                },
                selected = isSelected,
                onClick = {
                    bottomBarNavController.navigate(screen.route) {
                        popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                            saveState = false
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
