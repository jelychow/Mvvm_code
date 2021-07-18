package com.code.practice

import android.app.Application
import com.code.practice.database.UserNameDataBase
import com.code.practice.repositories.UserNameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())
    val userNameDB by lazy { UserNameDataBase.getDatabase(this, applicationScope) }
    val userNameRepo by lazy { UserNameRepository(userNameDB.userNameDao()) }

}