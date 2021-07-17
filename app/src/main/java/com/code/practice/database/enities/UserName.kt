package com.code.practice.database.enities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_name")
class UserName {

    @PrimaryKey
    @ColumnInfo(name = "name")
    var userName: String = ""
}

