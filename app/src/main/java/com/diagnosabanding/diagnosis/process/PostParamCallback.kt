package com.diagnosabanding.diagnosis.process

import com.diagnosabanding.model.DiagnoseField

interface PostParamCallback<T> {
    fun onPostSuccess(data: List<DiagnoseField>)
    fun onPostError()
}