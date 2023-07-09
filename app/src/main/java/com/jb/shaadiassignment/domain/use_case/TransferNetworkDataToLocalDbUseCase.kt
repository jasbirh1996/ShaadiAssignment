package com.jb.shaadiassignment.domain.use_case


import com.jb.shaadiassignment.data.dto.UserDetailDTO
import com.jb.shaadiassignment.domain.model.UserDetailData

import com.jb.shaadiassignment.domain.repository.LocalRepository
import com.jb.shaadiassignment.domain.repository.NetworkRepository
import com.jb.shaadiassignment.util.NetworkClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 * this use_case will make the network request
 * save the required data to db
 * then get the required to for presentation layer
 *
 *
 */

class TransferNetworkDataToLocalDbUseCase
@Inject constructor(val networkRepo: NetworkRepository, val localRepo: LocalRepository) {

    suspend fun transferNetworkDataToLocalData(): NetworkClass<Flow<List<UserDetailData>>> {
        try {
            val networkData = networkRepo.getNetworkData()
            val localData = networkData.data?.results?.map {
                UserDetailData(
                    id = it.login.uuid,
                    firstName = it.name.first,
                    lastName = it.name.last,
                    age = it.dob.age.toString(),
                    streetName = it.location.street.name,
                    city = it.location.city,
                    state = it.location.state,
                    acceptDeclinedStatus = null
                )
            }

            localRepo.insertUserData(localData!!)
            val getUserDataFromDb = localRepo.getUserDetailData()
            return NetworkClass.Success(getUserDataFromDb)


        } catch (e: HttpException) {
            return NetworkClass.Error(null, e.localizedMessage)
        } catch (e: IOException) {
            return NetworkClass.Error(null, e.localizedMessage)
        }
    }


}