package com.diagnosabanding.model

data class ResultResponse (
    var status: Int? = null,
    var message: Boolean? = null,
    var data: List<ResultField>
)