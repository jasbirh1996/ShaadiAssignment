package com.jb.shaadiassignment.presentation.accept_decline_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jb.shaadiassignment.domain.use_case.GetRequestDataUseCase
import com.jb.shaadiassignment.domain.use_case.TransferNetworkDataToLocalDbUseCase
import com.jb.shaadiassignment.domain.use_case.UpdateStatusUseCase
import com.jb.shaadiassignment.presentation.accept_decline_screen.state.UserState
import com.jb.shaadiassignment.util.NetworkClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val transferNetworkToLocalUseCase: TransferNetworkDataToLocalDbUseCase,
    private val updateUserStatusUseCase: UpdateStatusUseCase,
    private val getDataUseCase: GetRequestDataUseCase
) :
    ViewModel() {
    private var _state = mutableStateOf(UserState())
    val state = _state


    init {
        invoke()
    }

    private fun invoke() {
        viewModelScope.launch {
            transferNetworkToLocalUseCase.transferNetworkDataToLocalData().let { result ->
                when (result) {

                    is NetworkClass.Loading -> {
                        _state.value = UserState(isLoading = true)
                    }

                    is NetworkClass.Error -> {
                        _state.value = UserState(error = result.message.toString())
                    }

                    is NetworkClass.Success -> {
                        // success
                        getUserRequestData()

                    }
                }


            }


        }
    }

    private fun getUserRequestData() {
        getDataUseCase.getData().onEach {
            _state.value = UserState(it)
        }.launchIn(viewModelScope)
    }

    fun updateStatus(status: Boolean, id: String) {
        viewModelScope.launch {
            updateUserStatusUseCase.updateStatusInDb(status, id)
        }

    }

}