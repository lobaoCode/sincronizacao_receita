package br.com.bigwolf.SincronizacaoReceita;

import br.com.bigwolf.SincronizacaoReceita.model.ContaBancaria;
import br.com.bigwolf.SincronizacaoReceita.service.AtualizarContaService;
import br.com.bigwolf.SincronizacaoReceita.util.BibliotecaCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    private List<ContaBancaria> lista, listaAtualizada;
    private BibliotecaCSV bibliotecaCSV;

    public App(String[] args) {
        try {
            this.lista = BibliotecaCSV.convertToList(args.length == 0 ? "index.csv" : args[0]);
            this.listaAtualizada = Collections.synchronizedList(new ArrayList());
            init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println(args.length == 0 ?
                    "Arquivo padrão index.csv não encontrado!"
                    : "Arquivo " + args[0] + " não encontrado!");
        }
    }

    public synchronized void init () throws InterruptedException, IOException {
        Thread[] conjuntoThread = new Thread[this.lista.size()];
        int interador = 0;
        System.out.println("Iniciando a atualização das contas em paralelo!");
        for (ContaBancaria conta : this.lista) {
            conjuntoThread[interador] = new Thread(new AtualizarContaService(listaAtualizada, conta));
            conjuntoThread[interador].start();
            interador++;
        }
        for (int i = 0; i < conjuntoThread.length; i++) {
            conjuntoThread[i].join();
        }
        System.out.println("Atualização finalizada!");
        System.out.println("Exportando arquivo atualizado...");
        BibliotecaCSV.exportToCVS("listaAtualizada.csv", listaAtualizada);
        System.out.println("Exportação finalizada!");
    }

}
