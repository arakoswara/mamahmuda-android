package com.diagnosabanding.diagnosis.result

import com.diagnosabanding.model.ResultField

interface ResultCallback<T> {
    fun onSuccess(data: List<ResultField>)
    fun onError()
}