package com.schoters.gatotkaca.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schoters.gatotkaca.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel: ViewModel() {
    val listEverything = MutableLiveData<ArrayList<Everything>>()
    val listTop = MutableLiveData<ArrayList<Top>>()
    val errorEverythingText = MutableLiveData<String>()
    val errorTopText = MutableLiveData<String>()

    fun setEverything(parameterq: String) {
        val parameters = HashMap<String, String>()
        parameters["apiKey"] = "137f16d98f8445aeac86188caf4e42f6"
        parameters["q"] = parameterq
        RetrofitClient.instance.getEverything(parameters).enqueue(object :
            Callback<EverythingResponse> {
            override fun onResponse(
                call: Call<EverythingResponse>,
                response: Response<EverythingResponse>
            ) {
                listEverything.postValue(response.body()?.articles)
                errorEverythingText.value = ""
            }

            override fun onFailure(call: Call<EverythingResponse>, t: Throwable) {
                errorEverythingText.value = "Ups! Sepertinya ada masalah.\nMohon kembali lagi nanti."
            }
        })
    }

    fun setTop(parameterq: String) {
        val parameters = HashMap<String, String>()
        parameters["apiKey"] = "137f16d98f8445aeac86188caf4e42f6"
        parameters["q"] = parameterq
        RetrofitClient.instance.getTop(parameters).enqueue(object :
            Callback<TopResponse> {
            override fun onResponse(
                call: Call<TopResponse>,
                response: Response<TopResponse>
            ) {
                listTop.postValue(response.body()?.articles)
                errorTopText.value = ""
            }

            override fun onFailure(call: Call<TopResponse>, t: Throwable) {
                errorTopText.value = "Ups! Sepertinya ada masalah.\nMohon kembali lagi nanti."
            }
        })
    }

    fun getEverything(): LiveData<ArrayList<Everything>> {
        return listEverything
    }

    fun getErrorEverything(): LiveData<String> {
        return errorEverythingText
    }

    fun getTop(): LiveData<ArrayList<Top>> {
        return listTop
    }

    fun getErrorTop(): LiveData<String> {
        return errorTopText
    }
}