package com.arthurnagy.streamswithcoroutines

import android.net.Uri

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val profilePicture: Uri? = null
)