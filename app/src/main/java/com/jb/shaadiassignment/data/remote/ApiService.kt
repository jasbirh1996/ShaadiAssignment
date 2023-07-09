package com.jb.shaadiassignment.data.remote

import com.jb.shaadiassignment.data.dto.UserDetailDTO
import com.jb.shaadiassignment.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getUserApiData(
        @Query("results") data: String
    ):Response<UserDetailDTO>

}