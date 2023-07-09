package com.jb.shaadiassignment.presentation.accept_decline_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jb.shaadiassignment.domain.use_case.TransferNetworkDataToLocalDbUseCase
import com.jb.shaadiassignment.presentation.accept_decline_screen.state.UserState
import com.jb.shaadiassignment.util.NetworkClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val useCase: TransferNetworkDataToLocalDbUseCase) :
    ViewModel() {
    private var _state = mutableStateOf(UserState())
    val state = _state


    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            useCase.transferNetworkDataToLocalData().let { result ->
                when (result) {

                    is NetworkClass.Loading -> {
                        state.value = UserState(isLoading = true)
                    }

                    is NetworkClass.Error -> {
                        state.value = UserState(error = result.message.toString())
                    }

                    is NetworkClass.Success -> {
                        result.data?.onEach {
                            state.value = UserState(userDetailList = it)
                        }?.launchIn(viewModelScope)
                    }
                }


            }


        }
    }

}