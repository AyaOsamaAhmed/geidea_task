package com.aya.geidea_task.repo

import io.ktor.client.features.*
import android.util.Log
import com.aya.geidea_task.domain.response.DetailsResponse
import com.aya.geidea_task.domain.response.MainResponse
import com.aya.geidea_task.domain.services.MainServicesImpl

object MainRepo {


    suspend fun getAllUsers(): MainResponse?  {
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

    suspend fun getDetailsUser(id :Int): DetailsResponse?  {
        return try {
            val response =
                MainServicesImpl.getUserById(id)
            Log.d("MainRepo", "user details text : ${response.support.text}")
            response
        } catch (e:  ClientRequestException) {
            Log.d("MainRepo", "user details exception ${e.message}")
            null
        }
    }





}