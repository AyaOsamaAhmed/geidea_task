package com.aya.geidea_task.data.remote.apis

import com.aya.geidea_task.domain.response.MainResponse

interface MainApis {

    suspend fun getUsers(): MainResponse




}