package br.com.poli.campominado;

import java.util.Random;

public class Mapa {
	private int[][] campo;
	private Dificuldade dificuldade;
	
	public Mapa(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
		campo = new int [this.dificuldade.getValorDificuldade()][this.dificuldade.getValorDificuldade()];
		inicializaCampo();
		imprimeTela();
	}
	
	public int[][] getCampo() {
		return this.campo;
	}
	
	public void setCampo(int[][] campo){
		this.campo = campo;
	}
	
	public Dificuldade getDificuldade() {
		return this.dificuldade;
	}
	
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	public void inicializaCampo() {
		Random gerador = new Random();
		int contador = 10;
		int x;
		int y;
		
		while(contador != 0) {
			x = gerador.nextInt(campo.length);
			y = gerador.nextInt(campo.length);
			
			if (campo[x][y] == 0) {
				campo[x][y] = -1;
				contador--;
			}
		}
	}
	
	public void imprimeTela() {
		for (int i = 0; i < campo.length; i++) {
			System.out.println();
			for (int j = 0; j < campo.length; j++) {
				System.out.print(" " + campo[i][j]);
			}
		}
	}
}
