package br.com.poli.campominado;

import java.util.Scanner;

import br.com.poli.campominado.mapa.*;

public class CampoMinado {
	private Jogador jogador;
	private Mapa mapa;
	private Dificuldade dificuldade;
	
	public CampoMinado (Jogador jogador, Dificuldade dificuldade) {
		this.jogador = jogador;
		this.dificuldade = dificuldade;
		if (this.dificuldade == Dificuldade.FACIL) {
			this.mapa = new MapaFacil();
		}
		else if (this.dificuldade == Dificuldade.MEDIO) {
			this.mapa = new MapaMedio();
		}
		else {
			this.mapa = new MapaDificil();
		}
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public Mapa getMapa() {
		return mapa;
	}
	
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public Dificuldade getDificuldade() {
		return dificuldade;
	}
	
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	public void iniciarJogo() {
		Scanner teclado = new Scanner(System.in);
		int linha;
		int coluna;
		mapa.imprimirTela(false);
		
		while (this.mapa.isFimDeJogo() == false && this.mapa.isGanhouJogo() == false) {
			System.out.println("Digite a linha: ");
			linha = teclado.nextInt();
			System.out.println("Digite a coluna: ");
			coluna = teclado.nextInt();
			this.mapa.escolherPosicao(linha, coluna);
		}
		teclado.close();
	}
	
}
