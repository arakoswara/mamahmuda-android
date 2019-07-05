package com.diagnosabanding.model

data class HistoryResponse (
    var status: Int? = null,
    var message: Boolean? = null,
    var data: List<HistoryField>
)