package com.example.testmacropay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmacropay.core.model.BodyCleamsModel
import com.example.testmacropay.data.Repository
import com.example.testmacropay.ui.StatusRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    private var modelCleams = BodyCleamsModel(
        titular = "",
        url = "",
        email = "",
        solicitud = ""
    )
    val responseData : LiveData<Pair<StatusRequest,BodyCleamsModel?>>
    get() = _responseData
    private val _responseData = MutableLiveData<Pair<StatusRequest,BodyCleamsModel?>>(Pair(StatusRequest.NONE,null))

    fun setModel(modelToSet: BodyCleamsModel) {
        modelCleams = modelToSet
    }

    fun getModel() = modelCleams

    fun requestLogin(email: String, token: String) {
        viewModelScope.launch (Dispatchers.IO){
            _responseData.postValue(Pair(StatusRequest.LOADING,null))
            delay(3000)
        repository.requestBody(email, token){
            _responseData.postValue(it)
            }
        }
    }

}