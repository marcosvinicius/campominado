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
	
	public void inicializaCampo() {
		Random gerador = new Random();
		int contadorBombas = 0;
		int numeroGerado;
		int valor;
		
		if (this.dificuldade.getValorDificuldade() == 9) {
			valor = 4;
		}
		else {
			valor = 16;
		}
		
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo.length; j++) {
				numeroGerado = gerador.nextInt(valor) * (-1);
				
				if (numeroGerado == -1){
					contadorBombas++;
				}
				
				if ((contadorBombas < 11) && (numeroGerado == -1 || numeroGerado == 0)) {
					campo[i][j] = numeroGerado;
					
				}
				else {
					campo[i][j] = 0;
				}
			}
		}
	}
	
	public void imprimeTela() {
		for (int i = 0; i < campo.length; i++) {
			System.out.println();
			for (int j = 0; j < campo.length; j++) {
				System.out.print(campo[i][j]);
			}
		}
	}
}
