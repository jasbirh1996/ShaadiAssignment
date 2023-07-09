package com.jb.shaadiassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jb.shaadiassignment.domain.model.UserDetailData

/**
 * setting up Room db
 */

@Database(entities = [UserDetailData::class], version = 1)
abstract class UserRoomDb : RoomDatabase() {
    abstract fun dao(): UserDao
}