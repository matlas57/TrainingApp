package com.example.trainingapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trainingapp.view.TodayView

@Composable
fun Navigation(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = Screen.BottomScreen.Today.route) { //TODO:add start destination
        composable(Screen.BottomScreen.Today.bRoute) {
            TodayView()
        }
    }
}