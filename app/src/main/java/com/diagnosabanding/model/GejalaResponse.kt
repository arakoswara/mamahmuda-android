package com.diagnosabanding.model

data class GejalaResponse (
    var status: Int? = null,
    var message: Boolean? = null,
    var data: List<GejalaField>
)