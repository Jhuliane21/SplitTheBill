package com.example.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.splitthebill.R
import com.example.splitthebill.model.Constants.EXTRA_PESSOA
import com.example.splitthebill.model.Constants.VIEW_PESSOA
import com.example.splitthebill.databinding.ActivityMainBinding
import com.example.splitthebill.model.Pessoa
import com.example.splitthebill.adapter.pessoaAdapter

class MainActivity : AppCompatActivity() {
    private val pessoaList: MutableList<Pessoa> = mutableListOf();
    private lateinit var pessoaAdapter: pessoaAdapter

    private lateinit var carl: ActivityResultLauncher<Intent>


    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

     val total = amb.totalEt.text.toString();
     var quantidadePessoas = 0;
     fun getTotalPessoa() : Double {
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
                val person = result.data?.getParcelableExtra<Pessoa>(EXTRA_PESSOA)
                person?.let { _person->

                    if(pessoaList.any { it.id == _person.id }){
                        val posicao = pessoaList.indexOfFirst { it.id == _person.id }
                        pessoaList[posicao] = _person

                    }
                    else {
                        pessoaList.add(_person)
                    }
                    pessoaAdapter.notifyDataSetChanged()
                }
            }
        }
        registerForContextMenu(amb.pessoasLv)

        amb.pessoasLv.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val person = pessoaList[position]
                startActivity(
                    Intent(
                        this@MainActivity, ActivityAddPessoas::class.java
                    ).putExtra(
                        EXTRA_PESSOA, person
                    ).putExtra(
                        VIEW_PESSOA, true)
                )
            }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.context_menu, menu)
        return true
    }



}