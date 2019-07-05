package com.diagnosabanding.diagnosis.historyDetail

import com.diagnosabanding.model.HistoryDetailField

interface HistoryDetailCallback {
    fun onSuccess(data: List<HistoryDetailField>)
    fun onError()
}