package com.diagnosabanding.diagnosis.history

import com.diagnosabanding.model.HistoryField

interface HistoryCallback<T> {
    fun onSuccess(data: List<HistoryField>)
    fun onError()
}