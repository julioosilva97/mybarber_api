package com.mybarber.api.domain.service;

import java.util.List;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Servico;

public interface ServicoService {

    List<Servico> listar(int idBarbearia);

    void salvar(Servico servico);

    void excluir(int id);

    void atualizar(Servico servico);


}
