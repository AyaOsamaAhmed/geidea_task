package com.aya.geidea_task.utils


import android.app.Application
import com.aya.geidea_task.data.database.UserDataBase


val dbApp: UserDataBase by lazy {
    App.dbApp
}
    class App : Application() {

        companion object {
            lateinit var dbApp: UserDataBase


        }

        override fun onCreate() {
            super.onCreate()
              dbApp = UserDataBase.getInstance(applicationContext)

        }
 }