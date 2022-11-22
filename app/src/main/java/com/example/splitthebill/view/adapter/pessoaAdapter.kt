package com.example.splitthebill.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.splitthebill.R
import com.example.splitthebill.entity.Pessoa

public class pessoaAdapter(
    context: Context,
    private val pessoaList: MutableList<Pessoa>
) : ArrayAdapter<Pessoa>(context, R.layout.celulapessoa, pessoaList) {
    private data class TileContactHolder(val nomeTv: TextView, val valorTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = pessoaList[position]
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
                pessoaTileView.findViewById(R.id.valorEt),
            )
            pessoaTileView.tag = tileContactHolder
        }

        with(pessoaTileView?.tag as TileContactHolder) {
            nomeTv.text = contact.nome
            valorTv.text = contact.valor
        }

        return pessoaTileView
    }
}