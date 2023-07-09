package com.jb.shaadiassignment.domain.use_case


import android.util.Log
import com.jb.shaadiassignment.data.dto.UserDetailDTO
import com.jb.shaadiassignment.domain.model.UserDetailData

import com.jb.shaadiassignment.domain.repository.LocalRepository
import com.jb.shaadiassignment.domain.repository.NetworkRepository
import com.jb.shaadiassignment.util.NetworkClass
import com.jb.shaadiassignment.util.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
@Inject constructor(
    private val networkRepo: NetworkRepository,
    private val localRepo: LocalRepository,
    private val networkStatus: NetworkUtils
) {


    suspend fun transferNetworkDataToLocalData(): NetworkClass<UserDetailDTO> {
        try {
            NetworkClass.Loading(null)
            if (networkStatus.isNetworkAvailable()) {
                // making network request
                val networkData = networkRepo.getNetworkData()

                // mapping data for local

                val localData = networkData.data?.results?.map {
                    UserDetailData(
                        id = it.login.uuid,
                        firstName = it.name.first,
                        lastName = it.name.last,
                        age = it.dob.age.toString(),
                        streetName = it.location.street.name,
                        city = it.location.city,
                        state = it.location.state,
                        image = it.picture.medium,
                        acceptDeclinedStatus = null
                    )
                }
                // inserting data to local
                localRepo.insertUserData(localData!!)
                return NetworkClass.Success(networkData.data)
            }
            return NetworkClass.Success(
                null,
                "Net is off , local db should fetch data if it contains"
            )


        } catch (e: HttpException) {
            return NetworkClass.Error(null, e.localizedMessage)
        } catch (e: IOException) {
            return NetworkClass.Error(null, e.localizedMessage)
        }


    }


}






