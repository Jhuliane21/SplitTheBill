package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.splitthebill.Constants.EXTRA_PESSOA
import com.example.splitthebill.Constants.VIEW_PESSOA
import com.example.splitthebill.databinding.ActivityAddPessoasBinding
import com.example.splitthebill.model.entity.Pessoa

class ActivityAddPessoas : AppCompatActivity() {
    private val aap: ActivityAddPessoasBinding by lazy {
        ActivityAddPessoasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aap.root)

        val pessoaRecebida = intent.getParcelableExtra<Pessoa>(EXTRA_PESSOA)

        pessoaRecebida?.let {_pessoaRecebida ->
            with(aap){
                nomeEt.setText(_pessoaRecebida.nome)
                valorEt.setText(_pessoaRecebida.valor)
                comprasEt.setText(_pessoaRecebida.compra)
            }

        }
        val viewPessoa = intent.getBooleanExtra(VIEW_PESSOA, false)
        if(viewPessoa){
            aap.nomeEt.isEnabled = false
            aap.valorEt.isEnabled = false
            aap.comprasEt.isEnabled = false

            aap.saveBt.visibility = View.GONE
        }
        aap.voltarBt.setOnClickListener()
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        aap.saveBt.setOnClickListener {
            val pessoa = Pessoa(
                id = pessoaRecebida?.id,
                nome = aap.nomeEt.text.toString(),
                valor = aap.valorEt.text.toString(),
                compra = aap.comprasEt.text.toString(),
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PESSOA, pessoa)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}