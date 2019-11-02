package br.com.poli.campominado;

public enum Dificuldade {
	FACIL(9), MEDIO(16), DIFICIL(32);
	
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
