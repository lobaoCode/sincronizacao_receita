package br.com.bigwolf.SincronizacaoReceita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);
		new App(args);
	}

}
