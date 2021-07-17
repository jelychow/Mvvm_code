package com.code.practice.repositories

import com.code.practice.database.daos.UserNameDao
import com.code.practice.database.enities.UserName

class UserNameRepository(private val userNameDao: UserNameDao) {

     suspend fun insertUserName(name: UserName) {
         userNameDao.insertUserName(name)
    }

    suspend fun deleteUserName(name: UserName) {
        userNameDao.deleteUserName(name)
    }

     suspend fun getAllUserNames(): List<UserName> {
        return userNameDao.getAllUserNames()
    }
}