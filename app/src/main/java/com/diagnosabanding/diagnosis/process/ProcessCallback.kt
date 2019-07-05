package com.diagnosabanding.diagnosis.process

import com.diagnosabanding.model.GejalaField

interface ProcessCallback<T> {
    fun processSuccess(data: List<GejalaField>)
    fun processError()
}