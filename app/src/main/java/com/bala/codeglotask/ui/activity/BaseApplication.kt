package com.bala.codeglotask.ui.activity

import android.app.Application
import com.bala.codeglotask.dagger.AppComponent
import com.bala.codeglotask.dagger.AppModule
import com.bala.codeglotask.dagger.DaggerAppComponent
import com.bala.codeglotask.dagger.NetworkModule
import com.bala.codeglotask.network.NetworkBuildConfig


open class BaseApplication : Application() {
    open var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(NetworkBuildConfig.BASE_URL))
            .build()
    }

}