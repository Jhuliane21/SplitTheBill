package com.example.splitthebill.model.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
    @Entity
    data class Pessoa(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var nome: String,
    var valor: String,
    var compra: String,
    ): Parcelable

