package com.example.splitthebill.controller

import android.provider.SyncStateContract.Helpers.update
import androidx.room.Room
import com.example.splitthebill.model.entity.Pessoa
import com.example.splitthebill.model.entity.dao.PessoaRoomDao
import com.example.splitthebill.model.entity.dao.PessoaRoomDao.Constant.PESSOA_DATABASE_FILE
import com.example.splitthebill.model.entity.database.PessoaRoomDaoDatabase
import com.example.splitthebill.model.entity.database.PessoaRoomDaoImpl
import com.example.splitthebill.view.MainActivity

class PessoaRoomController (private val mainActivity: MainActivity){
    private val PessoaRoomDaoImpl: PessoaRoomDao by lazy {
        Room.databaseBuilder(
            mainActivity,
            PessoaRoomDaoDatabase::class.java,
            PESSOA_DATABASE_FILE
        ).build().getPessoaRoomDao()
    }

    fun insertContact(pessoa: Pessoa) {
        Thread {
            PessoaRoomDaoImpl.createPessoa(pessoa)
        }.start()
    }

    fun getPessoa(id: Int) = PessoaRoomDaoImpl.getPessoa(id)

    fun getPessoas() {
        Thread {

            val returnedList = PessoaRoomDaoImpl.getPessoas()
            mainActivity.updateListaPessoas(returnedList)
        }.start()
    }

    fun updatePessoa(pessoa: Pessoa) {
        Thread {
            PessoaRoomDaoImpl.updatePessoa(pessoa)
        }.start()
    }

    fun removeContact(pessoa: Pessoa) {
        Thread {
            PessoaRoomDaoImpl.deletePessoa(pessoa)
        }.start()
    }
}