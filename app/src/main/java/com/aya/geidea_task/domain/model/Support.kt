package com.aya.geidea_task.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Support (
    val url : String? = null,
    val text : String? = null
    )