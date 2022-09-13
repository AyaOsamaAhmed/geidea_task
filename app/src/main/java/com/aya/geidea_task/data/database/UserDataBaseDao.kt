package com.aya.geidea_task.data.database

import androidx.room.*
import com.aya.geidea_task.domain.model.User

@Dao
interface UserDataBaseDao  {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertUser(item: User)


    @Query("SELECT * FROM user ")
    suspend fun getUsers(): List<User>


}