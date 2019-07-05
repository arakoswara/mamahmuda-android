package com.diagnosabanding.diagnosis.process

import com.diagnosabanding.model.GejalaResponse

interface ProcessView: ProcessCallback<GejalaResponse> {
    fun showLoading()
    fun hideLoading()
}