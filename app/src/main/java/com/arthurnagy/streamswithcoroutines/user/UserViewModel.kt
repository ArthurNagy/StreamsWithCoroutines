package com.arthurnagy.streamswithcoroutines.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.arthurnagy.streamswithcoroutines.repository.user.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _user: LiveData<User> = liveData {
        userRepository.getUserFlow()
            .collect { user ->
                emit(user)
            }
    }
    val user: LiveData<UserUiModel> = _user.map { UserUiModel.from(it) }

//     val user: LiveData<UserUiModel> = userRepository.getUserFlow()
//         .map { user ->
//             UserUiModel.from(user)
//         }
//         .asLiveData()

    init {
        viewModelScope.launch {
            userRepository.getUserFlow()
                .map { user ->
                    UserUiModel.from(user)
                }
                .collect { userUiModel ->
                    // Use the data received on the UI thread
                }
        }
//        userRepository.getUser(object : DataCallback<User> {
//            override fun onResult(result: User) {
//                _user.value = result
//            }
//
//            override fun onError(throwable: Throwable) {
//                TODO("Handle error")
//            }
//        })
//        viewModelScope.launch(Dispatchers.Main) {
//            val user = withContext(Dispatchers.IO) {
//                userRepository.getUser()
//            }
//            // Update UI
//        }
    }

    fun fetchUser() {
        viewModelScope.launch {
            userRepository.getUser()
        }
    }

    fun updateUser(firstName: String, lastName: String, displayName: String, email: String) {
        viewModelScope.launch {
            val currentUser = _user.value
            val newUser = currentUser?.copy(
                firstName = firstName,
                lastName = lastName,
                displayName = displayName,
                email = email
            )
            newUser?.let { userRepository.saveUser(it) }
        }
    }
}