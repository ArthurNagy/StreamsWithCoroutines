package com.arthurnagy.streamswithcoroutines.repository.user

import com.arthurnagy.streamswithcoroutines.user.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@UseExperimental(ExperimentalCoroutinesApi::class)
class UserRepository(
    private val remoteSource: UserRemoteSource,
    private val localSource: UserLocalSource
) {

//    fun getUser(callback: DataCallback<User>) {
//        remoteSource.getUser(callback)
//    }

    fun getUserFlow(): Flow<User> = localSource.userFlow

    suspend fun getUser(): User = remoteSource.getUser().also { localSource.saveUser(it) }

    suspend fun saveUser(user: User) {
        val updatedUser = remoteSource.saveUser(user)
        localSource.saveUser(updatedUser)
    }
}