package com.arthurnagy.streamswithcoroutines.user

data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val displayName: String? = null,
    val email: String
)