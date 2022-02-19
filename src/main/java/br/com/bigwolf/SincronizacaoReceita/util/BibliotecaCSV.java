package br.com.bigwolf.SincronizacaoReceita.util;

import br.com.bigwolf.SincronizacaoReceita.model.ContaBancaria;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class BibliotecaCSV {

    //Converte uma lista .csv em um vetor sincronizado.
    public static List convertToList(String pDiretorio) throws IOException {
        List<ContaBancaria> lista = new Vector<>();

        CSVReader reader = new CSVReader(new FileReader(pDiretorio));

        String[] linha;
        reader.skip(1);
        while (true) {
            try {
                if (!((linha = reader.readNext()) != null)) break;
                ContaBancaria conta = new ContaBancaria();
                //Recuperando o valor da agencia
                conta.setAgencia(linha[0]);
                //Tratando e recuperado o valor da conta
                linha[1] = linha[1].replace("-", "");
                conta.setConta(linha[1]);
                //Trantando e recuperado o valor do saldo
                linha[2] = linha[2].replace(".", "").replace(",", ".");
                conta.setSaldo(Double.parseDouble(linha[2]));
                //Recuperando o valor do tipo da conta
                conta.setTipo(linha[3]);
                //Adicionando a lista
                lista.add(conta);
            } catch (CsvValidationException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    //Realiza a exportação de uma lista no formato .csv
    public static void exportToCVS(String pDiretorio, List<ContaBancaria> pLista) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(pDiretorio));
        for (ContaBancaria conta : pLista) {
            writer.writeNext(conta.toString().split(","));
        }
        writer.close();
    }
}
