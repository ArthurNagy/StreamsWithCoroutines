package com.arthurnagy.streamswithcoroutines.repository.user

import com.arthurnagy.streamswithcoroutines.User
import com.arthurnagy.streamswithcoroutines.repository.DataCallback

class UserRepository(
    private val remoteSource: UserRemoteSource,
    private val localSource: UserLocalSource
) {

    fun getUser(callback: DataCallback<User>) {
        remoteSource.getUser(callback)
    }

    suspend fun saveUser(user: User) {
        val updatedUser = remoteSource.saveUser(user)
        localSource.saveUser(updatedUser)
    }
}