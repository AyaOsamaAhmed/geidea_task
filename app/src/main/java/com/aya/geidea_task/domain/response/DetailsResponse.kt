package com.aya.geidea_task.domain.response

import com.aya.geidea_task.domain.model.Support
import com.aya.geidea_task.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DetailsResponse (

    @SerialName("data")
    val data: User ,

    @SerialName("support")
    val support: Support,




)