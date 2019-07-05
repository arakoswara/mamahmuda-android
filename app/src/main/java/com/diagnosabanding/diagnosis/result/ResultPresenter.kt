package com.diagnosabanding.diagnosis.result

import com.diagnosabanding.model.ResultField
import com.diagnosabanding.model.ResultResponse
import com.diagnosabanding.repository.MyRepository

class ResultPresenter(private val view: ResultView, private val myRepository: MyRepository) {
    fun getResult(deviceID : String, diagnosisID: String, pasienID: String) {
        myRepository.getResult(deviceID, diagnosisID, pasienID, object : ResultCallback<ResultResponse> {
            override fun onSuccess(data: List<ResultField>) {
                view.onSuccess(data)
            }

            override fun onError() {
                view.onError()
            }

        })
    }
}