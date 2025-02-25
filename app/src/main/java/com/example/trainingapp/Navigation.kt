package com.example.trainingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainingapp.view.LogView
import com.example.trainingapp.view.TodayView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavController,
    viewModel: MainViewModel,
    pd: PaddingValues
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Today.route,
        modifier = Modifier.padding(pd)
    ) { //TODO:add start destination
        composable(Screen.BottomScreen.Today.bRoute) {
            TodayView(viewModel)
        }
        composable(Screen.BottomScreen.Log.bRoute) {
            LogView(viewModel)
        }
        composable(Screen.AddExercise.route) {

        }
    }
}