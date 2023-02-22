package com.example.tabunganku

data class Items(
    var id: String? = null,
    var namaBarang: String? = null,
    var hargaBarang: String? = null,
    var uangNominal: String? = null,
    var intervalHarian: Boolean? = null,
    var intervalMingguan: Boolean? = null,
    var intervalTahunan: Boolean? = null
)