package com.example.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import com.example.splitthebill.R
import com.example.splitthebill.model.Pessoa
import com.example.splitthebill.view.MainActivity

class PessoaAdapter(
    context: Context,
    private val pessoasList: MutableList<Pessoa>,
    private val paraCal: Boolean = false
) : ArrayAdapter<Pessoa>(context, R.layout.celulapessoa, pessoasList) {
    private data class TilePersonHolder(
        val nomeTv: TextView, val valorPago: TextView, val diferenca: TextView
    )
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var quantidade = pessoasList.size
        val pessoa = pessoasList[position]
        var valorPorPessoa = pessoa.valorTotal?.div(quantidade)
        var pessoaCelulaView = convertView

        if (pessoaCelulaView == null) {
            pessoaCelulaView =
                (context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.celulapessoa,
                    parent,
                    false
                )

            val celulaPessoaHolder = TilePersonHolder(
                pessoaCelulaView.findViewById(R.id.nomeTv),
                pessoaCelulaView.findViewById(R.id.valorTv),
                pessoaCelulaView.findViewById(R.id.valorDiferenca),
            )
            pessoaCelulaView.tag = celulaPessoaHolder
        }
        var valorPagar = valorPorPessoa?.minus(pessoa.valorPagar)
        with(pessoaCelulaView?.tag as TilePersonHolder) {
            nomeTv.text = pessoa.nome
            valorPago.text = String.format("Pagou: R$ %.2f", pessoa.valorPagar)

            if(valorPagar!! > 0){
                diferenca.text = "Valor a Pagar: " + valorPagar
            }else if(valorPagar < 0){
                diferenca.text = "Valor a Receber: " + valorPagar
            }
        }

        return pessoaCelulaView
    }
}
