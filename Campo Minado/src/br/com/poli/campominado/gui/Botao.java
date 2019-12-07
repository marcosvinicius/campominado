package br.com.poli.campominado.gui;

import javax.swing.JButton;

public class Botao extends JButton {
	private int linha;
	private int coluna;
	
	public Botao (int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	
}
