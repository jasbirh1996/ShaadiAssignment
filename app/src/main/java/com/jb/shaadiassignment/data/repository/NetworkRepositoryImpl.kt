package com.jb.shaadiassignment.data.repository

import com.jb.shaadiassignment.data.dto.UserDetailDTO
import com.jb.shaadiassignment.data.remote.ApiService
import com.jb.shaadiassignment.domain.repository.NetworkRepository
import com.jb.shaadiassignment.util.Constants
import com.jb.shaadiassignment.util.NetworkClass
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val api: ApiService) : NetworkRepository {

    override suspend fun getNetworkData(): NetworkClass<UserDetailDTO> {
        val networkData = api.getUserApiData(Constants.RESULT_COUNT)
        val isSuccess =    networkData.isSuccessful

        if (isSuccess) {
            return NetworkClass.Success(networkData.body())
        }
        return NetworkClass.Error(null,networkData.message()?:"an unexpected network error occurred")
    }
}