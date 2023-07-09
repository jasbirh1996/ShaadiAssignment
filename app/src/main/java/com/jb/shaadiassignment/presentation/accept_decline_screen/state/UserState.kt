package com.jb.shaadiassignment.presentation.accept_decline_screen.state

import com.jb.shaadiassignment.domain.model.UserDetailData

data class UserState(
    val userDetailList : List<UserDetailData> =  emptyList(),
    val isLoading : Boolean = false,
    val error : String = "",
    val acceptDeclinedStatus : String? = null,
    val updateCompose : Boolean = false
)