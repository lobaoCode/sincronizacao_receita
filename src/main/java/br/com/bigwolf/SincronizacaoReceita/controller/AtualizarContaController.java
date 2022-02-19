package br.com.bigwolf.SincronizacaoReceita.controller;

import br.com.bigwolf.SincronizacaoReceita.model.ContaBancaria;
import br.com.bigwolf.SincronizacaoReceita.service.ReceitaService;

public class AtualizarContaController {

    private ReceitaService service;

    public AtualizarContaController() {
        this.service = new ReceitaService();
    }

    public ContaBancaria atualizarConta(ContaBancaria conta) {
        try {
            boolean status = service.atualizarConta(
                    conta.getAgencia(),
                    conta.getConta(),
                    conta.getSaldo(),
                    conta.getTipo());

            conta.setStatus(status ? "A" : "E");
        } catch (InterruptedException e) {
            conta.setStatus("F");
        }
        return conta;
    }
}
