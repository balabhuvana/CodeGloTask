package com.bala.codeglotask.repository

import androidx.lifecycle.MutableLiveData
import com.bala.codeglotask.model.BreedResponseModel
import com.bala.codeglotask.network.CodeGloApiWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CodeGloRepository @Inject constructor(
    private var codeGloApiWebService: CodeGloApiWebService
) {

    fun getDogBreedList(): MutableLiveData<BreedResponseModel> {

        val breedListLiveData = MutableLiveData<BreedResponseModel>()

        codeGloApiWebService.breedsList().enqueue(object : Callback<BreedResponseModel> {

            override fun onFailure(call: Call<BreedResponseModel>, t: Throwable) {
                val breedResponseModel = BreedResponseModel()
                breedResponseModel.success = false
                breedListLiveData.postValue(breedResponseModel)
            }

            override fun onResponse(
                call: Call<BreedResponseModel>,
                response: Response<BreedResponseModel>
            ) {
                val breedResponseModel: BreedResponseModel? = response.body()
                breedResponseModel?.success = true
                breedListLiveData.postValue(breedResponseModel)
            }
        })
        return breedListLiveData
    }

}