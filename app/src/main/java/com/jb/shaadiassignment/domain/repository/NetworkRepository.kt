package com.jb.shaadiassignment.domain.repository

import com.jb.shaadiassignment.data.dto.UserDetailDTO
import com.jb.shaadiassignment.util.NetworkClass

interface NetworkRepository {
    suspend fun getNetworkData():NetworkClass<UserDetailDTO>
}