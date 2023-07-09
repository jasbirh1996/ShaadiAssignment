package com.jb.shaadiassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.jb.shaadiassignment.domain.model.UserDetailData
import kotlinx.coroutines.flow.Flow

/**
 * setting up the dao to access the local db
 */


@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertUserDetail(userData: List<UserDetailData>?)

    @Query("SELECT * FROM UserDetailData")
    fun getUserDetailData(): Flow<List<UserDetailData>>

    @Query("UPDATE UserDetailData SET acceptDeclinedStatus = :status WHERE id = :id")
    suspend fun update(status: Boolean, id: String)
}