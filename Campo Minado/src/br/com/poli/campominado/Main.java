package br.com.poli.campominado;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		
		Jogador teste3 = new Jogador("Teste");
		// instanciando jogador
		System.out.println(teste3.getNome()); 
		// imprimindo nome do jogador
		
		CampoMinado teste4 = new CampoMinado(teste3, Dificuldade.DIFICIL);
		// instanciando CampoMinado
		
		System.out.println();
		
		teste4.getMapa().imprimeTela(); 
		// imprimindo o tabuleiro a partir de CampoMinado
	}
}
