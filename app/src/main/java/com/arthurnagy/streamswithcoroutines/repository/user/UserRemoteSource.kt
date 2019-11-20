package com.arthurnagy.streamswithcoroutines.repository.user

import com.arthurnagy.streamswithcoroutines.api.UserService
import com.arthurnagy.streamswithcoroutines.user.User

class UserRemoteSource(private val userService: UserService) {

//    fun getUser(callback: DataCallback<User>) {
//        userService.getUser().enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if (response.isSuccessful) {
//                    callback.onResult(response.body()!!)
//                } else {
//                    callback.onError(ApiException(response.code()))
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                callback.onError(t)
//            }
//        })
//    }

    suspend fun getUser(): User = userService.getUser()

    suspend fun saveUser(user: User): User {
        return userService.updateUser(user)
    }
}