package com.example.trainingapp

import android.app.Application

class TrainingApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}