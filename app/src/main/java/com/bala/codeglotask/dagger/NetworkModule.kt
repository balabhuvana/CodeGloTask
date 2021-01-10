package com.bala.codeglotask.dagger

import com.bala.codeglotask.network.CodeGloApiWebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class NetworkModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun getCodeGloApiWebService(retroFit: Retrofit): CodeGloApiWebService {
        retrofit.baseUrl()
        return retroFit.create(CodeGloApiWebService::class.java)
    }

    @get:Provides
    open val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}