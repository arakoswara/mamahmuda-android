package com.diagnosabanding.diagnosis.history

import com.diagnosabanding.model.HistoryResponse

interface HistoryCallback<T> {
    fun onSuccess(data: HistoryResponse)
    fun onError()
}