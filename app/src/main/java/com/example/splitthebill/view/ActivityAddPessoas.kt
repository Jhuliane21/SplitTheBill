package com.example.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.splitthebill.Constants.EXTRA_CONTACT
import com.example.splitthebill.Constants.VIEW_CONTACT
import com.example.splitthebill.databinding.ActivityAddPessoasBinding
import com.example.splitthebill.entity.Pessoa

class ActivityAddPessoas : AppCompatActivity() {
    private val aap: ActivityAddPessoasBinding by lazy {
        ActivityAddPessoasBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aap.root)

        val pessoaRecebida = intent.getParcelableExtra<Pessoa>(EXTRA_CONTACT)

        pessoaRecebida?.let {_pessoaRecebida ->
            with(aap){
                nomeEt.setText(_pessoaRecebida.nome)
                valorEt.setText(_pessoaRecebida.valor.toString())
                comprasEt.setText(_pessoaRecebida.compra)
            }

        }
        val viewContact = intent.getBooleanExtra(VIEW_CONTACT, false)
        if(viewContact){
            aap.nomeEt.isEnabled = false
            aap.valorEt.isEnabled = false
            aap.comprasEt.isEnabled = false

            aap.saveBt.visibility = View.GONE  //esconde o botão de salvar por n precisar dele aqui
        }
        aap.voltarBt.setOnClickListener()
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        aap.saveBt.setOnClickListener {
            val contact = Pessoa(
                id = pessoaRecebida?.id,
                nome = aap.nomeEt.text.toString(),
                valor = aap.valorEt.text.toString(),
                compra = aap.comprasEt.text.toString(),
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_CONTACT, contact)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}