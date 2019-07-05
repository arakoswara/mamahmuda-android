package com.diagnosabanding.model

data class DiagnoseResponse (
    var status: Int? = null,
    var message: String? = null,
    var success: Boolean? = false,
    var data: Int? = null
)