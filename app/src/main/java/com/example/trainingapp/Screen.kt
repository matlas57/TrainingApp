package com.example.trainingapp

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int)
        : Screen (bTitle, bRoute) {
        object Today: BottomScreen (
            "Today",
            "today",
            R.drawable.baseline_add_circle_24
        )

        object Log: BottomScreen (
            "Training Log",
            "log",
            R.drawable.baseline_calendar_month_24
        )

        object Browse: BottomScreen (
            "Browse Workouts",
            "browse",
            R.drawable.baseline_manage_search_24
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Log,
    Screen.BottomScreen.Today,
    Screen.BottomScreen.Browse
)