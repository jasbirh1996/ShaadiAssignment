package com.jb.shaadiassignment.fake_repositories

import com.jb.shaadiassignment.domain.model.UserDetailData
import com.jb.shaadiassignment.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeLocalRepository : LocalRepository {
    private val insertedData = mutableListOf<UserDetailData>()

    override suspend fun insertUserData(data: List<UserDetailData>) {
        insertedData.addAll(data)
    }

    override fun getUserDetailData(): Flow<List<UserDetailData>> {
        return flow {
            emit(insertedData)
        }
    }

    override suspend fun updateUserStatus(status: Boolean, id: String) {
        // Implement as needed for your use case
    }
}