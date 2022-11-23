package com.example.splitthebill.model.entity.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.splitthebill.model.entity.Pessoa

class pessoaDaoSqlite(context: Context): PessoaDao {
    companion object Constant {
        private const val PESSOAS_DATABASE_FILE = "pessoas"
        private const val PESSOA_TABLE = "pessoa"
        private const val ID_COLUMN = "id"
        private const val NOME_COLUMN = "nome"
        private const val VALOR_COLUMN = "valor"
        private const val COMPRAS_COLUNM = "compras"


        private const val CREATE_PESSOA_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $PESSOA_TABLE ( " +
                    " $ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " $NOME_COLUMN TEXT NOT NULL, " +
                    " $VALOR_COLUMN TEXT NOT NULL, " +
                    " $COMPRAS_COLUNM TEXT NOT NULL); "
    }
    private val pessoaSqliteDatabase: SQLiteDatabase
    init {
        //criar ou abrir o banco:
        pessoaSqliteDatabase = context.openOrCreateDatabase(
            PESSOAS_DATABASE_FILE,
            Context.MODE_PRIVATE,
            null
        )
        pessoaSqliteDatabase.execSQL(CREATE_PESSOA_TABLE_STATEMENT)
    }
    private fun Pessoa.toContentValues() = with(ContentValues()) {
        put(NOME_COLUMN, nome)
        put(VALOR_COLUMN, valor)
        put(COMPRAS_COLUNM, compra)
        this
    }
    private fun Cursor.rowToPessoa() = Pessoa(
        getInt(getColumnIndexOrThrow(ID_COLUMN)),
        getString(getColumnIndexOrThrow(NOME_COLUMN)),
        getString(getColumnIndexOrThrow(VALOR_COLUMN)),
        getString(getColumnIndexOrThrow(COMPRAS_COLUNM)),
    )
    override fun createPessoa(pessoa: Pessoa): Int = pessoaSqliteDatabase.insert(
        PESSOA_TABLE,
        null,
        pessoa.toContentValues()
    ).toInt()

    override fun getPessoa(id: Int): Pessoa? {
        val cursor = pessoaSqliteDatabase.rawQuery(
            "SELECT * FROM $PESSOA_TABLE WHERE $ID_COLUMN = ?",
            arrayOf(id.toString())
        )
        val contact = if (cursor.moveToFirst()) {
            cursor.rowToPessoa()
        } else {
            null
        }
        cursor.close()
        return contact
    }

    override fun getPessoas(): MutableList<Pessoa> {
        val pessoasList = mutableListOf<Pessoa>() //lista de contatos para ser populada e retornada
        //consulta de todos os contatos no banco:
        val cursor = pessoaSqliteDatabase.rawQuery(
            "SELECT * FROM $PESSOA_TABLE ORDER BY $NOME_COLUMN",
            null
        )
        //objeto cursor devolve os dados(percorre a tabela) em forma de objeto - moveToNext() vai passando pelas linhas e
        while (cursor.moveToNext()) {
            pessoasList.add(cursor.rowToPessoa()) //chama a função
        }

        return pessoasList

    }

    override fun updatePessoa(pessoa: Pessoa) = pessoaSqliteDatabase.update(
        PESSOA_TABLE,
        pessoa.toContentValues(),
        "$ID_COLUMN = ?",
        arrayOf(pessoa.id.toString())
    )

    override fun deletePessoa(pessoa: Pessoa) = pessoaSqliteDatabase.update(
        PESSOA_TABLE,
        pessoa.toContentValues(),
        "$ID_COLUMN = ?",
        arrayOf(pessoa.id.toString()))
    }
