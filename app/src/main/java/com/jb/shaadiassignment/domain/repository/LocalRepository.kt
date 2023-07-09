package com.jb.shaadiassignment.domain.repository

import com.jb.shaadiassignment.domain.model.UserDetailData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertUserData(data : List<UserDetailData>)
    fun getUserDetailData():Flow<List<UserDetailData>>
   suspend fun updateUserStatus(status : Boolean, id : String)
}