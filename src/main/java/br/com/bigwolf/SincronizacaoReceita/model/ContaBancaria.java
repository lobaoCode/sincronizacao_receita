package br.com.bigwolf.SincronizacaoReceita.model;

import lombok.Data;

@Data
public class ContaBancaria {
    private String agencia;
    private String conta;
    private double saldo;
    private String tipo;
    //A-ATUALIZADA, E-ERROR, F-FALHA
    private String status;

    private String getContaFormatada() {
        String[] conta = this.getConta().split("");
        String digitoVerificador = conta[5];
        conta[5] = "-";
        return String.join("", conta) + digitoVerificador;
    }

    public String toString() {
        return this.getAgencia()
                + "," + this.getContaFormatada()
                + "," + this.getSaldo()
                + "," + this.getTipo()
                + "," + this.getStatus();
    }
}
