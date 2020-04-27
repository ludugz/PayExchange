package com.tantn.payexchange.data.model

import com.google.gson.annotations.SerializedName

data class Quotes(
    @SerializedName("USDAED") val uSDAED: Double,
    @SerializedName("USDAFN") val uSDAFN: Double,
    @SerializedName("USDALL") val uSDALL: Double,
    @SerializedName("USDAMD") val uSDAMD: Double,
    @SerializedName("USDANG") val uSDANG: Double,
    @SerializedName("USDAOA") val uSDAOA: Double,
    @SerializedName("USDARS") val uSDARS: Double,
    @SerializedName("USDAUD") val uSDAUD: Double,
    @SerializedName("USDAWG") val uSDAWG: Double,
    @SerializedName("USDAZN") val uSDAZN: Double,
    @SerializedName("USDBAM") val uSDBAM: Double,
    @SerializedName("USDBBD") val uSDBBD: Double,
    @SerializedName("USDBDT") val uSDBDT: Double,
    @SerializedName("USDBGN") val uSDBGN: Double,
    @SerializedName("USDBHD") val uSDBHD: Double,
    @SerializedName("USDBIF") val uSDBIF: Double,
    @SerializedName("USDBMD") val uSDBMD: Double,
    @SerializedName("USDBND") val uSDBND: Double,
    @SerializedName("USDBOB") val uSDBOB: Double,
    @SerializedName("USDBRL") val uSDBRL: Double,
    @SerializedName("USDBSD") val uSDBSD: Double,
    @SerializedName("USDBTC") val uSDBTC: Double,
    @SerializedName("USDBTN") val uSDBTN: Double,
    @SerializedName("USDBWP") val uSDBWP: Double,
    @SerializedName("USDBYN") val uSDBYN: Double,
    @SerializedName("USDBYR") val uSDBYR: Double,
    @SerializedName("USDBZD") val uSDBZD: Double,
    @SerializedName("USDCAD") val uSDCAD: Double,
    @SerializedName("USDCDF") val uSDCDF: Double,
    @SerializedName("USDCHF") val uSDCHF: Double
    // First 30 items
)
