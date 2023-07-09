package com.jb.shaadiassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * setting entity that will be shown to presentation layer
 */

@Entity
data class UserDetailData(
    @PrimaryKey
    val id : String,
    val firstName : String,
    val lastName : String,
    val age : String,
    val streetName : String,
    val city: String,
    val state: String,
    val acceptDeclinedStatus : Boolean? = null

)
