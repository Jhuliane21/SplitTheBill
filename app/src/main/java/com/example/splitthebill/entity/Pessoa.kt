package com.example.splitthebill.entity

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
    @NonNull
    var nome: String,
    @NonNull
    var valor: String,
    @NonNull
    var compra: String,
    ): Parcelable

