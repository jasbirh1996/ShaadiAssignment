package com.jb.shaadiassignment.domain.use_case

import com.jb.shaadiassignment.domain.model.UserDetailData
import com.jb.shaadiassignment.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRequestDataUseCase @Inject constructor(private val localRepo : LocalRepository) {

    fun getData():Flow<List<UserDetailData>>{
        return localRepo.getUserDetailData()
    }
}