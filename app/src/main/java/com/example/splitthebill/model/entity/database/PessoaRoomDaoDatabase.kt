package com.example.splitthebill.model.entity.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.splitthebill.model.entity.Pessoa
import com.example.splitthebill.model.entity.dao.PessoaRoomDao


@Database(entities = [Pessoa::class], version = 1)
abstract class PessoaRoomDaoDatabase :RoomDatabase(){
    abstract fun getPessoaRoomDao(): PessoaRoomDao
}