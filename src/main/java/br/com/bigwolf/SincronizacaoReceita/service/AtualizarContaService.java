package br.com.bigwolf.SincronizacaoReceita.service;

import br.com.bigwolf.SincronizacaoReceita.App;
import br.com.bigwolf.SincronizacaoReceita.controller.AtualizarContaController;
import br.com.bigwolf.SincronizacaoReceita.model.ContaBancaria;
import br.com.bigwolf.SincronizacaoReceita.util.BibliotecaCSV;

import java.io.IOException;
import java.util.List;

public class AtualizarContaService implements Runnable {
    private AtualizarContaController controller;
    private List<ContaBancaria> listaAtualizada;
    private ContaBancaria conta;

    public AtualizarContaService (List<ContaBancaria> listaAtualizada, ContaBancaria conta) {
        this.controller = new AtualizarContaController();
        this.listaAtualizada = listaAtualizada;
        this.conta = conta;
    }

    @Override
    public void run() {
        atualizarConta();
    }

    private void atualizarConta() {
        ContaBancaria contaAtualizada = controller.atualizarConta(conta);
        listaAtualizada.add(contaAtualizada);
    }
}
