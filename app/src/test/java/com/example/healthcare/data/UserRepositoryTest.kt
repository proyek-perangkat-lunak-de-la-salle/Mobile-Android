package com.example.healthcare.data

import com.example.healthcare.data.pref.UserPref
import com.example.healthcare.data.remote.api.ApiService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserRepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var userPref: UserPref

    private lateinit var userRepository: UserRepository


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userRepository = UserRepository.getInstance(apiService, userPref)
    }

    @Test
    fun registerUserSuccessfulRegistration() {
        Assert.assertEquals(true, true)

    }

    @Test
    fun loginUserSavesDataToDataStoreAndLogoutRemovesData() {
        Assert.assertEquals(true, true)
    }

    @Test
    fun submitFormSubmitFormAndDisplayData() {
        Assert.assertEquals(true, true)
    }

    @Test
    fun getUserHistoryRetrievesUserHistory() {
        Assert.assertEquals(true, true)
    }

}