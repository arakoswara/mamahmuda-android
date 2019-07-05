package com.diagnosabanding.model

import com.google.gson.annotations.SerializedName

class ResultField {
    @SerializedName("diagnosis_id")
    var diagnosisId: Int? = null
    @SerializedName("diagnosis_name")
    var diagnosis_name: String? = null
    @SerializedName("solution_value")
    var solution_value: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("diagnosis_value")
    var diagnosis_value: String? = null
}