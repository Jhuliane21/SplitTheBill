package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.splitthebill.R
import com.example.splitthebill.adapter.PessoaAdapter

import com.example.splitthebill.databinding.ActivityMainBinding

import com.example.splitthebill.model.Constants.EXTRA_PESSOA

import com.example.splitthebill.model.Pessoa


class MainActivity<T> : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val pessoasList: MutableList<Pessoa> = mutableListOf()

    private lateinit var pessoaAdapter: PessoaAdapter

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        pessoaAdapter = PessoaAdapter(this, pessoasList)

        amb.pessoasLv.adapter = pessoaAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val pessoa = result.data?.getParcelableExtra<Pessoa>(EXTRA_PESSOA)
                pessoa?.let { _pessoa->

                    if(pessoasList.any { it.id == _pessoa.id }){
                        val pos = pessoasList.indexOfFirst { it.id == _pessoa.id }
                        pessoasList[pos] = _pessoa
                    }
                    else {
                        pessoasList.add(_pessoa)
                    }
                    pessoaAdapter.notifyDataSetChanged()

                }
            }
        }


        registerForContextMenu(amb.pessoasLv)

        amb.pessoasLv.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                startActivity(
                    Intent(
                        this@MainActivity, ActivityAddPessoas::class.java
                    )
                )
            }
    }

    fun getQuantidadePessoas(): Int{
        return pessoasList.size
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(/* menuRes = */ R.menu.activity_menu, /* menu = */ menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addPessoa -> {
                carl.launch(Intent(this, ActivityAddPessoas::class.java))
                true
            }

            else -> { false }
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when(item.itemId){
            R.id.removePersonMi -> {
                pessoasList.removeAt(position)
                pessoaAdapter.notifyDataSetChanged()
                true
            }
            R.id.editPersonMi -> {
                val pessoa = pessoasList[position]
                carl.launch(
                    Intent(this, ActivityAddPessoas::class.java).putExtra(EXTRA_PESSOA, pessoa)
                )
                true
            }
            else -> { false }
        }
    }

}