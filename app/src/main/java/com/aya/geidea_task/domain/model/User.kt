package com.aya.geidea_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int? = 0 ,

    val email : String? = null,
    val first_name : String? = null  ,
    val last_name : String? = null ,
    val avatar : String? = null

    )