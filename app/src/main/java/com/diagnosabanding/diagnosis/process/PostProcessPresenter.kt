package com.diagnosabanding.diagnosis.process

import android.util.Log
import com.diagnosabanding.model.DiagnoseField
import com.diagnosabanding.model.DiagnoseResponse
import com.diagnosabanding.repository.MyRepository

class PostProcessPresenter (private val view: PostParamView, private val myRepository: MyRepository) {
    fun postGejala(paramPost: MutableList<Int?>, paranName: String, paramDob : String, deviceID: String) {
        myRepository.postGejala(paramPost, paranName, paramDob, deviceID, object : PostParamCallback<DiagnoseResponse> {
            override fun onPostSuccess(data: List<DiagnoseField>) {
                view.onPostSuccess(data)
                Log.d("PostProcessPresenter ", data[0].pasien_id.toString())
            }

            override fun onPostError() {
                Log.d("ERROR PRESENTER ", true.toString())
                view.onPostError()
            }
        })
    }
}