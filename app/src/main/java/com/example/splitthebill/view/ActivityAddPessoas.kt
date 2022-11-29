package com.example.splitthebill.view

import android.content.Entity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.example.splitthebill.R
import com.example.splitthebill.model.Constants.EXTRA_PESSOA
import com.example.splitthebill.model.Constants.VIEW_PESSOA
import com.example.splitthebill.databinding.ActivityAddPessoasBinding
import com.example.splitthebill.model.Pessoa
import kotlin.random.Random

class ActivityAddPessoas : AppCompatActivity() {
    private val aap: ActivityAddPessoasBinding by lazy {
        ActivityAddPessoasBinding.inflate(layoutInflater)
    }
    private lateinit var amb: MainActivity<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aap.root)

        if (intent.getBooleanExtra("VIEW_PESSOA", false)) {
            aap.nomeEt.isEnabled = false
            aap.valorEt.isEnabled = false
            aap.comprasEt.isEnabled = false
            aap.saveBt.visibility = View.GONE
        }
        val pessoaRecebida = intent.getParcelableExtra<Pessoa>(EXTRA_PESSOA)

        pessoaRecebida?.let { _pessoaRecebida ->
            with(aap) {
                nomeEt.setText(_pessoaRecebida.nome)
                valorEt.setText(_pessoaRecebida.valorPagar.toString())
                comprasEt.setText(_pessoaRecebida.compras)
            }

        }
        val viewPessoa = intent.getBooleanExtra(VIEW_PESSOA, false)
        if (viewPessoa) {
            aap.nomeEt.isEnabled = false
            aap.valorEt.isEnabled = false
            aap.comprasEt.isEnabled = false

            aap.saveBt.visibility = View.GONE
        }


        aap.saveBt.setOnClickListener {
            if (
                aap.nomeEt.text.isNotEmpty() &&
                aap.valorEt.text.isNotEmpty()
            ) {
                val person = Pessoa(
                    id = pessoaRecebida?.id ?: Random(System.currentTimeMillis()).nextInt(),
                    nome = aap.nomeEt.text.toString(),
                    valorPagar = aap.valorEt.text.toString().toDouble(),
                    valorTotal = aap.valorTotalEt.text.toString().toDouble(),
                    compras = aap.comprasEt.text.toString(),
                    )
                setResult(
                    RESULT_OK,
                    Intent().putExtra(
                        EXTRA_PESSOA,
                        person
                    )
                )
                finish()
            }
        }
    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_secondary, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when(item.itemId) {
                R.id.closeMi -> {
                    finish()
                    true
                }
                else -> { false }
            }
    }
}