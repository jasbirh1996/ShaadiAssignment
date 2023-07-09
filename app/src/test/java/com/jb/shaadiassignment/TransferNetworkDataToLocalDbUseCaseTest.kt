package com.jb.shaadiassignment

import com.jb.shaadiassignment.domain.model.UserDetailData
import com.jb.shaadiassignment.fake_repositories.FakeLocalRepository
import com.jb.shaadiassignment.fake_repositories.FakeNetworkRepository
import com.jb.shaadiassignment.util.NetworkClass
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TransferNetworkDataToLocalDbUseCaseTest {

    private lateinit var networkRepo: FakeNetworkRepository
    private lateinit var localRepo: FakeLocalRepository

    @Before
    fun setup() {
        networkRepo = FakeNetworkRepository()
        localRepo = FakeLocalRepository()
    }

    @Test
    fun `test making network call and checking the success case`() = runBlocking {
        // Set up the network repository to return success
        networkRepo.setShouldGiveException(false)

        // Make the network call and get the result
        val data = networkRepo.getNetworkData()

        // Verify that the network call was successful
        assertTrue(data is NetworkClass.Success)


    }

    @Test
    fun `test making network call and testing exception`() = runBlocking {
        // Set up the network repository to return success
        networkRepo.setShouldGiveException(true)

        // Make the network call and get the result
        val data = networkRepo.getNetworkData()

        // Verify that the network call was Failed and got exception
        assertTrue(data is NetworkClass.Error)


    }



    @Test()
    fun `test the local db insertion and getting the data`() = runBlocking {
        // Get the user detail data from the success response
        val userDetailData = mutableListOf<UserDetailData>()
        val item = UserDetailData(
            id = "",
            firstName = "",
            lastName = "",
            age = "",
            streetName = "",
            city = "",
            state = "",
            image = "",
        )
        userDetailData.add(item)

        // Insert the user detail data into the local repository
        localRepo.insertUserData(userDetailData)

        // Get the user detail data from the local repository
        val savedData = localRepo.getUserDetailData().first()

        // Verify that the saved data matches the original data
        assertEquals(userDetailData, savedData)

    }





}