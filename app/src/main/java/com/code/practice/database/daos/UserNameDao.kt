package com.code.practice.database.daos

import androidx.room.*
import com.code.practice.database.enities.UserName

@Dao
interface UserNameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserName(username: UserName)


    @Delete
    suspend fun deleteUserName(username: UserName)

    @Query("SELECT * FROM user_name")
    suspend fun getAllUserNames(): List<UserName>
}