package com.arthurnagy.streamswithcoroutines.user

data class UserUiModel(var firstName: String, var lastName: String, var displayName: String, var email: String) {
    companion object {
        fun from(user: User): UserUiModel = UserUiModel(
            firstName = user.firstName,
            lastName = user.lastName,
            displayName = if (user.displayName.isNullOrEmpty()) "${user.firstName} ${user.lastName}" else user.displayName,
            email = user.email
        )
    }
}