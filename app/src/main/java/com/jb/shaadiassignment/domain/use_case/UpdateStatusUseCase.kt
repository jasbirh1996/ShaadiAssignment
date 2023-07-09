package com.jb.shaadiassignment.domain.use_case

import com.jb.shaadiassignment.data.local.UserDao
import com.jb.shaadiassignment.domain.repository.LocalRepository
import javax.inject.Inject

class UpdateStatusUseCase @Inject constructor(private val repo : LocalRepository) {

   suspend fun updateStatusInDb(status : Boolean, id :String){
        repo.updateUserStatus(status, id)
    }
}