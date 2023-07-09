package com.jb.shaadiassignment.data.repository

import com.jb.shaadiassignment.data.local.UserDao
import com.jb.shaadiassignment.domain.model.UserDetailData
import com.jb.shaadiassignment.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: UserDao) : LocalRepository {

    override suspend fun insertUserData(data: List<UserDetailData>) {
        dao.insertUserDetail(data)
    }

    override fun getUserDetailData(): Flow<List<UserDetailData>> {
        return dao.getUserDetailData()
    }

    override suspend fun updateUserStatus(status: Boolean, id: String) {
        dao.update(status, id)
    }
}