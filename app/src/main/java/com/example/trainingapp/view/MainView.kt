package com.example.trainingapp.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
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
import com.example.trainingapp.Screen
import com.example.trainingapp.screensInBottom

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ResourceAsColor")
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

    val dialogOpen = remember { mutableStateOf(false) }

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
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title.value
                    )
                },
                contentColor = Color.White,
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = bottomBar,
        floatingActionButton = {
            if (title.value == Screen.BottomScreen.Today.bTitle) {
                FloatingActionButton(
                    onClick = { dialogOpen.value = true}, //Open dialog to enter new exercise
                    contentColor = Color.White,
                    backgroundColor = Color(0xFF6200EE)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Navigation(
            pd = it
        )
        AddExerciseView(viewModel, dialogOpen)
    }
}