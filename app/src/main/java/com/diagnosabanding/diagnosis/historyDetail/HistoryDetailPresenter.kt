package com.diagnosabanding.diagnosis.historyDetail

import com.diagnosabanding.model.HistoryDetailField
import com.diagnosabanding.repository.MyRepository

class HistoryDetailPresenter(
    private val view: HistoryDetailView, private val myRepository: MyRepository
) {
    fun getHistoryPasien(deviceID: String, pasienID: String) {
        myRepository.getHistoryPasien(deviceID, pasienID, object: HistoryDetailCallback {
            override fun onSuccess(data: List<HistoryDetailField>) {
                view.onSuccess(data)
            }

            override fun onError() {
                view.onError()
            }
        })
    }
}