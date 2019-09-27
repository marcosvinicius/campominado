package br.com.poli.campominado;

public enum Dificuldade {
	FACIL(9), DIFICIL(16);
	
	private int valor;
	
	private Dificuldade(int valor) {
		this.valor = valor;
	}
	
	public int getValorDificuldade() {
		return this.valor;
	}
	
	public void setValorDificuldade(int valor) {
		this.valor = valor;
	}
}
