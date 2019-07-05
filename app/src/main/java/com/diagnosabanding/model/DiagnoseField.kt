package com.diagnosabanding.model

import com.google.gson.annotations.SerializedName

data class DiagnoseField (
    @SerializedName("diagnosis_id")
    var diagnosis_id: Int? = null
){
}