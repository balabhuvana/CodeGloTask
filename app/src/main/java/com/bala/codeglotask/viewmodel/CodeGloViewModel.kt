package com.bala.codeglotask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bala.codeglotask.model.BreedResponseModel
import com.bala.codeglotask.repository.CodeGloRepository
import javax.inject.Inject

class CodeGloViewModel @Inject constructor(
    application: Application,
    private var codeGloRepository: CodeGloRepository
) : AndroidViewModel(application) {

    private var breedListLiveData: LiveData<BreedResponseModel>? = null

    fun getDogBreedListViewModel() {
        breedListLiveData = codeGloRepository.getDogBreedList()
    }

    fun observeDogBreedList(): LiveData<BreedResponseModel>? {
        return breedListLiveData
    }
}