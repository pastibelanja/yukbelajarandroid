package com.disarpus.penjualan.data.model

import com.disarpus.penjualan.data.model.DataLogin
import com.google.gson.annotations.SerializedName

data class ResponseLogin (

    @SerializedName("status") val status : Boolean,
    @SerializedName("msg") val msg : String,
    @SerializedName("pegawai") val pegawai : DataLogin?
)