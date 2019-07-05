package com.diagnosabanding.diagnosis.process

import com.diagnosabanding.model.DiagnoseResponse

interface PostParamCallback {
    fun onPostSuccess(data: DiagnoseResponse?)
    fun onPostError()
}