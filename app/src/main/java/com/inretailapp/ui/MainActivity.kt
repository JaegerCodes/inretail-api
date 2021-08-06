package com.inretailapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.inretailapp.R
import com.inretailapp.app.providers.DataStoreProvider
import com.inretailapp.ui.home.HomeActivity
import com.inretailapp.ui.login.AuthActivity
import com.inretailapp.app.util.startNewActivity
import org.koin.android.ext.android.getKoin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataStoreProvider: DataStoreProvider = getKoin().get()

        dataStoreProvider.accessToken.asLiveData().observe(this, {
            val activity = if (it == "") AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}