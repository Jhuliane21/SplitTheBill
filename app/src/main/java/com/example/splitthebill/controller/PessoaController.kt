package com.example.splitthebill.controller

import com.example.splitthebill.model.entity.dao.PessoaDao
import com.example.splitthebill.model.entity.dao.pessoaDaoSqlite
import com.example.splitthebill.model.entity.Pessoa
import com.example.splitthebill.view.MainActivity

class pessoaController (private val mainActivity: MainActivity) {
        private val pessoaDaoImpl: PessoaDao = pessoaDaoSqlite(mainActivity)

        fun insertPessoa(pessoa: Pessoa) = pessoaDaoImpl.createPessoa(pessoa)

        fun getPessoa(id:Int) = pessoaDaoImpl.getPessoa(id)

        fun getPessoas(){
            Thread{

                val returnedList = pessoaDaoImpl.getPessoas()
                mainActivity.updateListaPessoas(returnedList)
            }.start()
        }

        fun editPessoa(pessoa: Pessoa) = pessoaDaoImpl.updatePessoa(pessoa)

        fun deletePessoa(pessoa: Pessoa) = pessoaDaoImpl.deletePessoa(pessoa)
}