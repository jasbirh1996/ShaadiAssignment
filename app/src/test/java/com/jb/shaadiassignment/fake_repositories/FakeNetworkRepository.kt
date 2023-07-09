package com.jb.shaadiassignment.fake_repositories

import com.jb.shaadiassignment.data.dto.UserDetailDTO
import com.jb.shaadiassignment.domain.repository.NetworkRepository
import com.jb.shaadiassignment.util.NetworkClass

class FakeNetworkRepository : NetworkRepository {
    private var shouldGiveException = false

    fun setShouldGiveException(value: Boolean) {
        shouldGiveException = value
    }

    override suspend fun getNetworkData(): NetworkClass<UserDetailDTO> {
        if (shouldGiveException) {
            return NetworkClass.Error(null, "unexpected error")
        }
        return NetworkClass.Success()
    }
}