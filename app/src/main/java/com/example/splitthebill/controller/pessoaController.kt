package com.example.splitthebill.controller

import com.example.splitthebill.dao.PessoaDao
import com.example.splitthebill.database.pessoaDaoSqlite
import com.example.splitthebill.entity.Pessoa
import com.example.splitthebill.view.MainActivity

class pessoaController (private val mainActivity: MainActivity) {
        private val pessoaDaoImpl: PessoaDao = pessoaDaoSqlite(mainActivity)

        fun insertPessoa(pessoa: Pessoa) = pessoaDaoImpl.createPessoa(pessoa)

        fun getContacts(){
            Thread{

                val returnedList = pessoaDaoImpl.getPessoas()
                mainActivity.updateListaPessoas(returnedList)
            }.start()
        }

        fun editPessoa(pessoa: Pessoa) = pessoaDaoImpl.updatePessoa(pessoa)

        fun removePessoa(id: Int) = pessoaDaoImpl.deletePessoa(id)
}