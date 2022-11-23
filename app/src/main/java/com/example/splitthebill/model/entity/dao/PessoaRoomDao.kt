package com.example.splitthebill.model.entity.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.splitthebill.model.entity.Pessoa

interface PessoaRoomDao {
        companion object Constant {
            const val PESSOA_DATABASE_FILE = "pessoas_room"
            const val PESSOA_TABLE = "pessoas"
            const val ID_COLUMN = "id"
            const val NOME_COLUMN = "nome"

        }

        @Insert
        fun createPessoa(pessoa: Pessoa)

        @Query("SELECT * FROM $PESSOA_TABLE WHERE $ID_COLUMN = :id")
        fun getPessoa(id: Int): Pessoa?

        @Query("SELECT * FROM $PESSOA_TABLE ORDER BY $NOME_COLUMN")
        fun getPessoas():MutableList<Pessoa>

        @Update
        fun updatePessoa(pessoa: Pessoa): Int
        @Delete
        fun deletePessoa(pessoa: Pessoa):Int

    }
