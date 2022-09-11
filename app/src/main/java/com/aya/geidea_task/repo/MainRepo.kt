package com.aya.geidea_task.repo

import io.ktor.client.features.*
import android.util.Log
import com.aya.geidea_task.domain.response.MainResponse
import com.aya.geidea_task.domain.services.MainServicesImpl

object MainRepo {


    suspend fun activities(): MainResponse?  {
        return try {
            val response =
                MainServicesImpl.getUsers()
            Log.d("MainRepo", "user text : ${response.support.text}")
            response
        } catch (e:  ClientRequestException) {
            Log.d("MainRepo", "user exception ${e.message}")
           null
        }
    }





}