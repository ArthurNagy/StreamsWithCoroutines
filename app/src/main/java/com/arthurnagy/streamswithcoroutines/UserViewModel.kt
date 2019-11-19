package com.arthurnagy.streamswithcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arthurnagy.streamswithcoroutines.repository.DataCallback
import com.arthurnagy.streamswithcoroutines.repository.user.UserRepository

class UserViewModel(userRepository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        userRepository.getUser(object : DataCallback<User> {
            override fun onResult(result: User) {
                _user.value = result
            }

            override fun onError(throwable: Throwable) {
                TODO("Handle error")
            }
        })
    }
}