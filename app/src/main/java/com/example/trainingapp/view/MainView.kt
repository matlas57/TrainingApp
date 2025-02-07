package com.example.trainingapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trainingapp.MainViewModel
import com.example.trainingapp.Navigation
import com.example.trainingapp.screensInBottom

@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
//    val scope:CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()

    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val title = remember { mutableStateOf(currentScreen.title) }

    val bottomBar: @Composable () -> Unit = {
        BottomNavigation(
            Modifier.wrapContentSize()
        ) {
            screensInBottom.forEach { item ->
                val isSelected = currentRoute == item.bRoute
                val tint = if (isSelected) Color.LightGray else Color.White
                BottomNavigationItem(
                    modifier = Modifier.padding(bottom = 32.dp),
                    selected = isSelected,
                    onClick = {
                        controller.navigate(item.bRoute)
                        title.value = item.bTitle
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(item.icon),
                            contentDescription = item.bTitle,
                            tint = tint
                        )
                    },
                    selectedContentColor = Color.LightGray,
                    unselectedContentColor = Color.White
                )
            }
        }
    }

    Scaffold(
        topBar = {},
        bottomBar = bottomBar,
        scaffoldState = scaffoldState
    ) {
        Navigation(
            pd = it
        )
    }
}