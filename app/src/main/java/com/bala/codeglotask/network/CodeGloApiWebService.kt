package com.bala.codeglotask.network

import com.bala.codeglotask.model.BreedResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface CodeGloApiWebService {

    @GET("api/breeds/list")
    fun breedsList(): Call<BreedResponseModel>

}