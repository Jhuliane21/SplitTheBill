package com.example.splitthebill.model.entity.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.splitthebill.model.entity.Pessoa;
import com.example.splitthebill.model.entity.dao.PessoaRoomDao;

import java.util.List;

public class PessoaRoomDaoImpl implements PessoaRoomDao {
    @Override
    public void createPessoa(@NonNull Pessoa pessoa) {

    }

    @Nullable
    @Override
    public Pessoa getPessoa(int id) {
        return null;
    }

    @NonNull
    @Override
    public List<Pessoa> getPessoas() {
        return null;
    }

    @Override
    public int updatePessoa(@NonNull Pessoa pessoa) {
        return 0;
    }

    @Override
    public int deletePessoa(@NonNull Pessoa pessoa) {
        return 0;
    }
}
