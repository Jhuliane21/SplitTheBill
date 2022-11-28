package com.example.splitthebill.model

import android.os.Parcelable
import android.text.Editable

import kotlinx.parcelize.Parcelize

@Parcelize
data class Pessoa(
    val id: Int?,
    var nome: String,
    var valorPagar: Double,
    var diferenca: Double,
    var compras: String,
    ): Parcelable

