package com.diagnosabanding.repository

import com.diagnosabanding.api.MamahMudaApi
import com.diagnosabanding.diagnosis.history.HistoryCallback
import com.diagnosabanding.diagnosis.historyDetail.HistoryDetailCallback
import com.diagnosabanding.diagnosis.process.PostParamCallback
import com.diagnosabanding.diagnosis.process.ProcessCallback
import com.diagnosabanding.diagnosis.result.ResultCallback
import com.diagnosabanding.helper.RetrofitHelper
import com.diagnosabanding.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MyRepository {
    fun getGejala(callback: ProcessCallback<GejalaResponse>) {
        RetrofitHelper
            .createService(MamahMudaApi::class.java)
            .getGejala("gejala")
            .enqueue(object : Callback<GejalaResponse> {
                override fun onFailure(call: Call<GejalaResponse>, t: Throwable) {
                    callback.processError()
                }

                override fun onResponse(call: Call<GejalaResponse>, response: Response<GejalaResponse>) {
                    response.body()?.data?.let { callback.processSuccess(it) }
                }

            })
    }

    fun postGejala(paramPost: MutableList<Int?>, paramName: String, paramDob: String, deviceID: String, callback: PostParamCallback) {
        RetrofitHelper
            .createService(MamahMudaApi::class.java)
            .postGejala("process", paramPost, paramName, paramDob, deviceID)
            .enqueue(object: Callback<DiagnoseResponse> {
                override fun onFailure(call: Call<DiagnoseResponse>, t: Throwable) {
                    callback.onPostError()
                }

                override fun onResponse(call: Call<DiagnoseResponse>, response: Response<DiagnoseResponse>) {
                    callback.onPostSuccess(response.body())
                }

            })
    }

    fun getResult(deviceID: String, diagnosisID: String, pasienID: String, callback: ResultCallback<ResultResponse>) {
        RetrofitHelper
            .createService(MamahMudaApi::class.java)
            .getResult("result/${deviceID}/${diagnosisID}/${pasienID}")
            .enqueue(object : Callback<ResultResponse> {
                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                    callback.onError()
                }

                override fun onResponse(call: Call<ResultResponse>, response: Response<ResultResponse>) {
                    response.body()?.data?.let { callback.onSuccess(it) }
                }

            })
    }

    fun getHistory(deviceID: String, callback: HistoryCallback<HistoryResponse>) {
        RetrofitHelper
            .createService(MamahMudaApi::class.java)
            .getHistory("pasien/"+deviceID)
            .enqueue(object : Callback<HistoryResponse> {
                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    callback.onError()
                }

                override fun onResponse(call: Call<HistoryResponse>, response: Response<HistoryResponse>) {
                    response.body()?.data?.let { callback.onSuccess(it) }
                }

            })
    }

    fun getHistoryPasien(deviceID: String, pasienID: String, callback: HistoryDetailCallback) {
        RetrofitHelper
            .createService(MamahMudaApi::class.java)
            .getHistoryPasien("history-pasien/${deviceID}/${pasienID}")
            .enqueue(object : Callback<HistoryDetailResponse> {
                override fun onFailure(call: Call<HistoryDetailResponse>, t: Throwable) {
                    callback.onError()
                }

                override fun onResponse(call: Call<HistoryDetailResponse>, response: Response<HistoryDetailResponse>) {
                    response.body()?.data?.let { callback.onSuccess(it) }
                }
            })
    }
}