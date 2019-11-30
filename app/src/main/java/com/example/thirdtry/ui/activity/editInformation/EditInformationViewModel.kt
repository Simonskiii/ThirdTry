package com.example.thirdtry.ui.activity.editInformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.model.Information

class EditInformationViewModel: ViewModel() {
    fun editInformation(token: String, Id: String, name: String, gender: String,
                        birthday: String, type: String): MutableLiveData<Information> =
        RetrofitClient.serviceApi.editInformation("JWT $token", Id, name, gender,birthday, type)

}