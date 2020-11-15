package com.mybarber.api.domain.service;


import com.mybarber.api.domain.entity.Promocao;

public interface PromocaoService {

    void salvar(Promocao promocao);

    void editar(Promocao promocao);

    Promocao buscarPromocao(int idServico);

    void alterarStatus(Promocao promocao);
}
