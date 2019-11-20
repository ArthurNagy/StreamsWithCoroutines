package com.arthurnagy.streamswithcoroutines.api

import android.os.Handler
import android.os.Looper
import com.arthurnagy.streamswithcoroutines.user.User
import kotlinx.coroutines.delay
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.Executors

interface UserService {
//    @GET("/user/me")
//    fun getUser(): Call<User>

    @POST("/user/update")
    suspend fun updateUser(user: User): User

    @GET("/user/me")
    suspend fun getUser(): User
}

class UserServiceImpl : UserService {

    override suspend fun updateUser(user: User): User {
        delay(500)
        return user
    }

    override suspend fun getUser(): User {
        delay(500)
        return MOCK_USER
    }

//    override fun getUser(): Call<User> = MockCall()

    private class MockCall : Call<User> {
        private val networkExectur = Executors.newFixedThreadPool(3)
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun enqueue(callback: Callback<User>) {
            networkExectur.execute {
                Thread.sleep(500)
                mainThreadHandler.post {
                    callback.onResponse(this, Response.success(MOCK_USER))
                }
            }
        }

        override fun isExecuted(): Boolean = false

        override fun clone(): Call<User> = this

        override fun isCanceled(): Boolean = false

        override fun cancel() = Unit

        override fun execute(): Response<User> {
            return Response.success(MOCK_USER)
        }

        override fun request(): Request = Request.Builder().build()
    }

    private companion object {
        private val MOCK_USER = User(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            email = "me@johndoe.com"
        )
    }
}