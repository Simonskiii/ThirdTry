package com.example.thirdtry.ui.activity.getInformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.model.Good
import com.example.thirdtry.model.Information

class GetInformationViewModel: ViewModel() {
    fun getInformation(token: String, id: String): MutableLiveData<Information> =
        RetrofitClient.serviceApi.getInformation("JWT $token", id)
}