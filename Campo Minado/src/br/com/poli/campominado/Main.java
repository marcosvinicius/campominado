package br.com.poli.campominado;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		
		Mapa teste1 = new Mapa(Dificuldade.FACIL);
		System.out.println();
		Mapa teste2 = new Mapa(Dificuldade.DIFICIL);
		// gerando os tabuleiros facil e dificil
		
		System.out.println();
		System.out.println();
		
		Jogador teste3 = new Jogador("Teste");
		// instanciando jogador
		System.out.println(teste3.getNome()); // imprimindo nome do jogador
		
		CampoMinado teste4 = new CampoMinado(teste3, teste1, Dificuldade.FACIL);
		// instanciando CampoMinado
		teste4.getMapa().imprimeTela(); // imprimindo o tabuleiro a partir de CampoMinado
	}
}
