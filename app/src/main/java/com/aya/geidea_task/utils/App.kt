package com.aya.geidea_task.utils


import android.app.Application
import com.aya.geidea_task.data.database.UserDataBase

    class App : Application() {

        companion object {
            lateinit var databaseApp: UserDataBase


        }

        override fun onCreate() {
            super.onCreate()
              databaseApp = UserDataBase.getInstance(applicationContext)

        }
 }