package com.example.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.Constants.EXTRA_CONTACT
import com.example.splitthebill.Constants.VIEW_CONTACT
import com.example.splitthebill.controller.pessoaController
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.entity.Pessoa
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

    public val total = amb.totalEt.text.toString();
    public var quantidadePessoas = 0;
    public fun getTotalPessoa() : Double {
        return total.toDouble()/quantidadePessoas;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        pessoaAdapter = pessoaAdapter(this, pessoaList)
        amb.pessoasLv.adapter = pessoaAdapter
        quantidadePessoas = pessoaList.size
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
        amb.pessoasLv.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val pessoa = pessoaList[position]

                val pessoaIntent = Intent(this@MainActivity, ActivityAddPessoas::class.java)
                pessoaIntent.putExtra(EXTRA_CONTACT, pessoa)
                pessoaIntent.putExtra(VIEW_CONTACT, true)
                startActivity(pessoaIntent)
            }
        }

        pessoaController.getPessoas();

    }
    fun updateListaPessoas(_contactList: MutableList<Pessoa>) {
        pessoaList.clear()
        pessoaList.addAll(_contactList)
        pessoaAdapter.notifyDataSetChanged()
    }

}