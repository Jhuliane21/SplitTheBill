package com.example.splitthebill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.model.Pessoa
import com.example.splitthebill.view.MainActivity

public class pessoaAdapter(
    context: Context,
    private val pessoaList: MutableList<Pessoa>
) : ArrayAdapter<Pessoa>(context, R.layout.celulapessoa, pessoaList) {
    private data class TileContactHolder(
        val nomeTv: TextView,
        val valorTv: TextView,
        val diferencaTv: TextView
    )
    private lateinit var amb: MainActivity



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val total = amb.getTotalPessoa();
        val pessoa = pessoaList[position]
        var pessoaTileView = convertView
        if (pessoaTileView == null) {

            pessoaTileView =
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.celulapessoa,
                    parent,
                    false
                )

            val tileContactHolder = TileContactHolder(
                pessoaTileView.findViewById(R.id.nomeTv),
                pessoaTileView.findViewById(R.id.valorTv),
                pessoaTileView.findViewById(R.id.diferencaTv),
            )
            pessoaTileView.tag = tileContactHolder
        }

        with(pessoaTileView?.tag as TileContactHolder) {
            nomeTv.text = pessoa.nome
            valorTv.text = pessoa.valorPagar.toString()
            diferencaTv.text = total.toString()
        }

        return pessoaTileView
    }


}