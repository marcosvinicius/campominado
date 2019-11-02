package br.com.poli.campominado;

import java.util.ArrayList;
import java.util.List;

public class Celula {
	private boolean bandeira;
	private boolean bomba;
	private int qtdBombasVizinhas;
	private boolean visivel;
	private int linha;
	private int coluna;
	private List<Celula> vizinhos = new ArrayList<Celula>();
	
	public Celula(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public boolean isBandeira() {
		return this.bandeira;
	}
	
	public void setBandeira(boolean bandeira) {
		this.bandeira = bandeira;
	}
	
	public boolean isBomba() {
		return this.bomba;
	}
	
	public void setBomba(boolean bomba) {
		this.bomba = bomba;
	}
	
	public int getQtdBombasVizinhas() {
		return this.qtdBombasVizinhas;
	}
	
	public void setQtdBombasVizinhas(int qtdBombasVizinhas) {
		this.qtdBombasVizinhas = qtdBombasVizinhas;
	}
	
	public boolean isVisivel() {
		return this.visivel;
	}
	
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	
	public int getLinha() {
		return this.linha;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	public int getColuna() {
		return this.coluna;
	}
	
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public void buscarVizinhos(Celula[][] campo) {
		for (int i = linha - 1; i < linha + 1; i++) {
			for (int j = coluna - 1; j <= coluna+1; j++) {
				if (i >= 0 && j >= 0 && i < campo.length && j < campo.length && i != linha && j != coluna) {
					vizinhos.add(campo[i][j]);
				}
				
			}
		}
	}
	
	public boolean isEmBranco() {
		if (qtdBombasVizinhas == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}
