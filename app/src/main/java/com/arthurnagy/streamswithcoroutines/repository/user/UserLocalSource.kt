package com.arthurnagy.streamswithcoroutines.repository.user

import com.arthurnagy.streamswithcoroutines.user.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow

@UseExperimental(ExperimentalCoroutinesApi::class, FlowPreview::class)
class UserLocalSource {

    private val userChannel: BroadcastChannel<User> = BroadcastChannel(Channel.CONFLATED)
    val userFlow = userChannel.asFlow()

    suspend fun saveUser(user: User) {
        userChannel.send(user)
    }
}