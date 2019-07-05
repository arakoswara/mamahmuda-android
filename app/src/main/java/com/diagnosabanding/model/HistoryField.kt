package com.diagnosabanding.model

import com.google.gson.annotations.SerializedName

class HistoryField {
    @SerializedName("pasien_id")
    var pasien_id : Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("dob")
    var dob: String? = null
    @SerializedName("age")
    var age: String? = null
    @SerializedName("created_at")
    var created_at: String? = null
}