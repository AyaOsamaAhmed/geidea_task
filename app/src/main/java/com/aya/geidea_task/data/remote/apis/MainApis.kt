package com.aya.geidea_task.data.remote.apis

import com.aya.geidea_task.domain.response.DetailsResponse
import com.aya.geidea_task.domain.response.MainResponse

interface MainApis {

    suspend fun getUsers(): MainResponse

    suspend fun getUserById( id : Int ): DetailsResponse




}