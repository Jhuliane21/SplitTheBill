package com.example.splitthebill.dao

import com.example.splitthebill.entity.Pessoa

interface PessoaDao {

    fun createPessoa(contact: Pessoa):Int

    fun getPessoa(id: Int): Pessoa?

    fun getPessoas():MutableList<Pessoa>

    fun updatePessoa(contact: Pessoa): Int

    fun deletePessoa(id: Int):Int

}