package com.bala.codeglotask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BreedResponseModel {

    @SerializedName("message")
    @Expose
    var breedList: Array<String>? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
}