package com.diagnosabanding.diagnosis.history

import com.diagnosabanding.model.HistoryResponse
import com.diagnosabanding.repository.MyRepository

class HistoryPresenter(private val view: HistoryView, private val myRepository: MyRepository) {
    fun getHistory(deviceID: String) {
        myRepository
            .getHistory(deviceID, object : HistoryCallback<HistoryResponse> {
                override fun onSuccess(data: HistoryResponse) {
                    view.onSuccess(data)
                }

                override fun onError() {
                    view.onError()
                }
            })
    }

}