package com.example.splitthebill.model.entity.dao

import com.example.splitthebill.model.entity.Pessoa

interface PessoaDao {

    fun createPessoa(pessoa: Pessoa):Int

    fun getPessoa(id: Int): Pessoa?

    fun getPessoas():MutableList<Pessoa>

    fun updatePessoa(pessoa: Pessoa): Int

    fun deletePessoa(pessoa: Pessoa): Int

}