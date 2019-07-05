package com.diagnosabanding.diagnosis.process

import com.diagnosabanding.model.DiagnoseResponse
import com.diagnosabanding.repository.MyRepository

class PostProcessPresenter (private val view: PostParamView, private val myRepository: MyRepository) {
    fun postGejala(paramPost: MutableList<Int?>, paranName: String, paramDob : String, deviceID: String) {
        myRepository.postGejala(paramPost, paranName, paramDob, deviceID, object : PostParamCallback {
            override fun onPostSuccess(data: DiagnoseResponse?) {
                view.onPostSuccess(data)
            }

            override fun onPostError() {
                view.onPostError()
            }
        })
    }
}