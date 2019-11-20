package com.arthurnagy.streamswithcoroutines.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.arthurnagy.streamswithcoroutines.R
import com.arthurnagy.streamswithcoroutines.UserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {

    private val viewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(DataBindingUtil.setContentView<UserBinding>(this, R.layout.activity_main)) {
            lifecycleOwner = this@UserActivity
            viewModel = this@UserActivity.viewModel
        }
        lifecycleScope.launchWhenStarted {
            viewModel.fetchUser()
        }
    }
}
