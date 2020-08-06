package com.mybarber.api.domain.service;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.mybarber.api.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.TokenDeVerificacao;
import com.mybarber.api.domain.entity.Usuario;
import com.mybarber.api.domain.repository.BarbeariaDAO;
import com.mybarber.api.domain.repository.EnderecoDAO;
import com.mybarber.api.domain.repository.FuncionarioDAO;
import com.mybarber.api.domain.repository.HorarioAtendimentoDAO;
import com.mybarber.api.domain.repository.PerfilDAO;
import com.mybarber.api.domain.repository.TokenDeVerificacaoDAO;
import com.mybarber.api.domain.repository.UsuarioDAO;
import com.mybarber.api.domain.repository.UsuarioPerfilDAO;
import com.mybarber.api.domain.util.Cargo;
import com.mybarber.api.domain.util.EnviarEmail;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    EnderecoDAO daoEndereco;

    @Autowired
    FuncionarioDAO daoFuncionario;

    @Autowired
    UsuarioDAO daoUsuario;

    @Autowired
    UsuarioPerfilDAO daoUsuarioPerfil;

    @Autowired
    BarbeariaDAO daoBarbearia;

    @Autowired
    PerfilDAO daoPerfil;

    @Autowired
    HorarioAtendimentoDAO horarioAtendimentoDAO;

    @Autowired
    TokenDeVerificacaoService tokenService;

    @Autowired
    private EnviarEmail enviarEmail;

    @Override
    @Transactional
    public void salvar(Map<String, Object> map) {

        var funcionario = (Funcionario) map.get("funcionario");
        var primeiroFuncionario = (Boolean) map.get("primeiroFuncionario");

        var login = funcionario.getUsuario().getLogin();
        if (daoUsuario.buscarPorLogin(login) == null) {

            if (!daoFuncionario.verificarEmail(funcionario.getEmail())) {

                if (funcionario.getEndereco() != null) {
                    funcionario.setEndereco(daoEndereco.salvar(funcionario.getEndereco()));
                }

                if (primeiroFuncionario == true) {
                    //salvar barbearia
                    funcionario.getBarbearia().setEndereco(daoEndereco.salvar(funcionario.
                            getEndereco()));
                    funcionario.setBarbearia(daoBarbearia.salvar(funcionario.getBarbearia()));
                    //dados do usuario
                    funcionario.getUsuario().getPerfil().setDescricao("ADMINISTRADOR");
                    funcionario.setCargo(Cargo.BARBEIRO);
                }

                daoUsuario.salvar(funcionario.getUsuario());
                daoFuncionario.salvar(funcionario);
                daoUsuarioPerfil.salvar(funcionario.getUsuario());

               // var token = tokenService.criarToken(funcionario);

                //enviarEmail.ativarConta(funcionario, token);

            } else {
                throw new NegocioException("Email já existente.");
            }

        } else {
            throw new NegocioException("Login já existente.");
        }
    }

    @Override
    public void alterar(Funcionario funcionario) {

        if (funcionario.getEndereco() != null) {
            daoEndereco.alterar(funcionario.getEndereco());
        }

        daoFuncionario.alterar(funcionario);

        //para pegar o id e senha do usuario
        Usuario usuario = daoUsuario.buscar(funcionario.getUsuario().getId());
        if (usuario.getSenha() != "") {
            funcionario.getUsuario().setSenha(usuario.getSenha());
        }
        daoUsuarioPerfil.editar(usuario);
        daoUsuario.alterar(funcionario.getUsuario());
    }


    @Override
    public List<Funcionario> listar(Barbearia barbearia) {

        return daoFuncionario.listar(barbearia.getId());
    }


    @Override
    public Funcionario buscar(int id) {


        var funcionario = daoFuncionario.buscar(id);

        return funcionario;
    }


    @Override
    public void excluir(int id) {

        Funcionario funcionario = new Funcionario();
        funcionario = buscar(id);

        if (funcionario.getUsuario().isAtivo() == true) {

            horarioAtendimentoDAO.excluir(funcionario.getId());
            daoFuncionario.excluir(funcionario);
            daoUsuarioPerfil.excluir(funcionario.getUsuario());
            daoUsuario.excluir(funcionario.getUsuario());
            daoEndereco.excluir(funcionario.getEndereco());

        } else {
            //tokenDAO.deletarPorIdUsuario(funcionario.getUsuario().getId());
            daoFuncionario.excluir(funcionario);
            daoUsuarioPerfil.excluir(funcionario.getUsuario());
            daoUsuario.excluir(funcionario.getUsuario());
            daoEndereco.excluir(funcionario.getEndereco());
        }


        ///passar get endere�o como parametro
    }


    @Override
    public List<Funcionario> listarPorCargo(Map<String, Object> map) {

        var cargo = (String) map.get("cargo");
        var idBarbearia = (int) map.get("idBarbearia");

        if(checkCargo(cargo)){
            return daoFuncionario.listarPorCargo(Cargo.valueOf(cargo), idBarbearia);
        }else{
            throw new NegocioException("Nome cargo : "+cargo +" inválido");
        }
    }

    public boolean checkCargo(String cargo) {

        for (Cargo c : Cargo.values()) {
            if (c.name().equals(cargo)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Funcionario buscarPorIdUsuario(int idUsuario) {

        return daoFuncionario.buscarPorIdUsuario(idUsuario);
    }

    @Override
    public boolean verificarEmail(String email) {

        return daoFuncionario.verificarEmail(email);
    }

}
