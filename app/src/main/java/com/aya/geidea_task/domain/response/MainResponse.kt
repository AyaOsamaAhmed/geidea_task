package com.aya.geidea_task.domain.response

import com.aya.geidea_task.domain.model.Support
import com.aya.geidea_task.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MainResponse (

    @SerialName("page")
    val page: Int,

    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,

    @SerialName("data")
    val data: ArrayList<User> ,

    @SerialName("support")
    val support: Support,




)