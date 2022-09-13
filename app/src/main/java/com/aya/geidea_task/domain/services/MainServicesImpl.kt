package com.aya.geidea_task.domain.services

import com.aya.geidea_task.data.remote.apis.MainApis
import com.aya.geidea_task.data.remote.client.KtorClient
import com.aya.geidea_task.domain.response.DetailsResponse
import com.aya.geidea_task.domain.response.MainResponse
import io.ktor.client.request.*

object MainServicesImpl : MainApis {

    private val httpClient by lazy {
        KtorClient.getInstance
    }

    override suspend fun getUsers(): MainResponse {
       return httpClient.get(path = "api/users?per_page=20")
    }

    override suspend fun getUserById(id: Int): DetailsResponse {
        return httpClient.get(path = "api/users/"){
            parameter("id",id)
        }

    }


}