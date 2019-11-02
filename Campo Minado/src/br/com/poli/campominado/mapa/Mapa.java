package br.com.poli.campominado.mapa;

import java.util.Random;
import br.com.poli.campominado.*;

public abstract class Mapa {
	private Celula[][] campo;
	private Dificuldade dificuldade;
	private int bombas;
	private boolean fimDeJogo;
	private boolean ganhouJogo;
	private int celulasVisiveis;
	
	public Mapa(int tamanho, int bombas) {
		this.campo = new Celula[tamanho][tamanho];
		this.bombas = bombas;
		inicializarCelulas();
		distribuirBombas(bombas);
		contarBombas();
	}
	
	public Celula[][] getCampo() {
		return this.campo;
	}
	
	public void setCampo(Celula[][] campo){
		this.campo = campo;
	}
	
	public Dificuldade getDificuldade() {
		return this.dificuldade;
	}
	
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	public Celula getCelula(int linha, int coluna) {
		return campo[linha][coluna];
	}
	
	public boolean isFimDeJogo() {
		return this.fimDeJogo;
	}
	
	public boolean isGanhouJogo() {
		return this.ganhouJogo;
	}
	
	public int getCelulasVisiveis() {
		return this.celulasVisiveis;
	}
	
	private void distribuirBombas(int bombas) {	
		Random gerador = new Random();
		int x;
		int y;
		
		while (bombas != 0) {
			x = gerador.nextInt(campo.length); 
			y = gerador.nextInt(campo.length);
			
			if (campo[x][y].isBomba() == false) {
				campo[x][y].setBomba(true);
				bombas--;
			}
		}
	}
	
	private void inicializarCelulas() {
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo.length; j++) {
				campo[i][j] = new Celula(i, j);
			}
		}
		
		
	}
	
	public void imprimirTela(boolean teste) {
		if (teste == false) {
			for (int i = 0; i < campo.length; i++) {
				for (int j = 0; j < campo.length; j++) {
					if (campo[i][j].isVisivel()) {
						System.out.print("  " + campo[i][j].getQtdBombasVizinhas() + " ");
					}
					else {
						System.out.print(" __ ");
					}
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("Quantidade de celulas visiveis: " + celulasVisiveis);
			
		}
		else {
			for (int i = 0; i < campo.length; i++) {
				for (int j = 0; j < campo.length; j++) {
					System.out.print(" " + campo[i][j].isBomba());
				}
				System.out.println();
			}
		}
	}
	
	public void contarBombas() {
		int quantidadeBombas;
		
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo.length; j++) {
				quantidadeBombas = 0;
				
				if (i > 0) {
					if (campo[i-1][j].isBomba()) {
						quantidadeBombas++;
					}
				}
				if (i+1 < campo.length) {
					if (campo[i+1][j].isBomba()) {
						quantidadeBombas++;
					}
				}
				if (j > 0) {
					if (campo[i][j-1].isBomba()) {
						quantidadeBombas++;
					}
				}
				if (j+1 < campo.length) {
					if (campo[i][j+1].isBomba()) {
						quantidadeBombas++;
					} // os quatro primeiros verificam se tem bomba em cima, embaixo, do lado esquerdo e do lado direito.
				}
				if (i > 0 && j > 0) {
					if (campo[i-1][j-1].isBomba()) {
						quantidadeBombas++;
					}
				}
				if (i > 0 && j+1 < campo.length) {
					if (campo[i-1][j+1].isBomba()) {
						quantidadeBombas++;
					}
				}
				if (i+1 < campo.length && j > 0) {
					if (campo[i+1][j-1].isBomba()) {
						quantidadeBombas++;
					}
				}
				if (i+1 < campo.length && j+1 < campo.length) {
					if (campo[i+1][j+1].isBomba()) {
						quantidadeBombas++;
					}// esses quatros conferem as diagonais
				}
				campo[i][j].setQtdBombasVizinhas(quantidadeBombas);
				System.out.println("Linha: " + i +  " Coluna: " + j + " Bombas vizinhas: " + campo[i][j].getQtdBombasVizinhas());
			}
		}
	}
	
	public void escolherPosicao(int linha, int coluna) {
		if (campo[linha][coluna].isBomba()) {
			this.fimDeJogo = true;
			System.out.println("Fim de jogo. Você perdeu!");
		}
		else if (campo[linha][coluna].getQtdBombasVizinhas() != 0) {
			campo[linha][coluna].setVisivel(true);
			this.celulasVisiveis++;
			imprimirTela(false);	
		}
		else if ((campo[linha][coluna].getQtdBombasVizinhas() == 0) && (campo[linha][coluna].isBomba() == false)) {
			revelarEspacos(campo[linha][coluna]);
			imprimirTela(false);
		}
		this.ganhouJogo = verificarGanhouJogo();
	}
	
	private void revelarEspacos(Celula celulaEscolhida) {
		celulaEscolhida.setVisivel(true);
		celulasVisiveis++;
		
		for (int i = celulaEscolhida.getLinha() - 1; i <= celulaEscolhida.getLinha() + 1 ; i++) {
			for (int j = celulaEscolhida.getColuna() - 1; j <= celulaEscolhida.getColuna() + 1; j++) {
				if (i >= 0 && j >= 0 && i < campo.length && j < campo.length) {
					if(campo[i][j].getQtdBombasVizinhas() == 0 && campo[i][j].isVisivel() == false) {
						revelarEspacos(campo[i][j]);
					}
					else if(campo[i][j].isVisivel() == false){
						campo[i][j].setVisivel(true);
						celulasVisiveis++;
					}
				}
			}
		}
	}
	
	private boolean verificarGanhouJogo() {
		int contador = 0;
		
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo.length; j++) {
				if (campo[i][j].isVisivel()) {
					contador++;
				}
			}
		}
		
		if (contador >= ((campo.length*campo.length) - this.bombas)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}