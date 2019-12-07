package br.com.poli.campominado.jogo;

public class Jogador {
	private String nome;
	private String tempo;

	public Jogador (String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTempo() {
		return this.tempo;
	}
	
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
}
