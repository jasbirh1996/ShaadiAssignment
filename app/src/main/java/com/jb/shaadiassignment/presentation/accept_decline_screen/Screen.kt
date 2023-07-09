package com.jb.shaadiassignment.presentation.accept_decline_screen

sealed class Screen(val route: String) {
    object AcceptDeclineScreen: Screen("accept_decline_screen")

}