package com.example.splitthebill.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.Constants.EXTRA_CONTACT
import com.example.splitthebill.controller.pessoaController
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.entity.Pessoa
import com.example.splitthebill.view.adapter.pessoaAdapter

class MainActivity : AppCompatActivity() {
    private val pessoaList: MutableList<Pessoa> = mutableListOf();
    private lateinit var pessoaAdapter: pessoaAdapter

    private lateinit var carl: ActivityResultLauncher<Intent>

    private val pessoaController: pessoaController by lazy {
        pessoaController(this)
    }

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        pessoaAdapter = pessoaAdapter(this, pessoaList)
        amb.pessoasLv.adapter = pessoaAdapter
        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val pessoa = result.data?.getParcelableExtra<Pessoa>(EXTRA_CONTACT)
                pessoa?.let { _pessoa ->

                    if (pessoaList.any { it.id == _pessoa.id }) {
                        val position = pessoaList.indexOfFirst { it.id == _pessoa.id }
                        pessoaList[position] = _pessoa

                    } else {
                        //inserir novo contato no banco
                        val newId = pessoaController.insertPessoa(_pessoa)
                        //inserir novo contato no fim da lista
                        pessoaList.add(_pessoa)

                    }
                    pessoaList.sortBy { it.nome } //ordenar contatos por nome
                    pessoaAdapter.notifyDataSetChanged() //avisa pro adapter que houveram mudanças no data source
                }
            }
            amb.btAdd.setOnClickListener()
            {
                val intent = Intent(this, ActivityAddPessoas::class.java)
                startActivity(intent)
            }
        }

    }
    fun updateListaPessoas(_contactList: MutableList<Pessoa>) {
        pessoaList.clear()
        pessoaList.addAll(_contactList)
        pessoaAdapter.notifyDataSetChanged()
    }

}