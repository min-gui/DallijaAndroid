package com.mingui.dallija.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

@AndroidEntryPoint
class SplashActivity : FragmentActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    private val stringFlow = flow {
        for (i in 0..1000){
            emit("integer: $i")
            kotlinx.coroutines.delay(1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            stringFlow.collect{
                println(it)
            }
        }
    }

}