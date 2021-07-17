package com.code.practice.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.code.practice.database.daos.UserNameDao
import com.code.practice.database.enities.UserName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val USERNAME_DATABASE_NAME = "username_database"

@Database(entities = [UserName::class], version = 1)
abstract class UserNameDataBase : RoomDatabase() {

    abstract fun userNameDao(): UserNameDao

    companion object {
        @Volatile
        private var INSTANCE: UserNameDataBase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): UserNameDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserNameDataBase::class.java,
                    USERNAME_DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        Log.d(USERNAME_DATABASE_NAME, "init")
                    }
                }
            }
        }

    }

}