package com.diagnosabanding.api

import com.diagnosabanding.model.*
import retrofit2.Call
import retrofit2.http.*

interface MamahMudaApi {

    @GET
    fun getGejala(@Url url: String): Call<GejalaResponse>

    @GET
    fun getResult(@Url url: String): Call<ResultResponse>

    @GET
    fun getHistory(@Url url: String): Call<HistoryResponse>

    @GET
    fun getHistoryPasien(@Url url: String): Call<HistoryDetailResponse>

    @FormUrlEncoded
    @POST
    fun postGejala(
        @Url url: String,
        @Field("param_gejala[]") param_gejala: MutableList<Int?>,
        @Field("param_child_name") param_child_name: String,
        @Field("param_child_dob") param_child_dob: String,
        @Field("device_id") device_id: String
    ) : Call<DiagnoseResponse>
}