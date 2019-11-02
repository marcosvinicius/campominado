package br.com.poli.campominado;

public class Main {
	public static void main(String[] args) {
		
		Jogador jogador = new Jogador("João");
		CampoMinado jogo = new CampoMinado(jogador, Dificuldade.DIFICIL);
		jogo.iniciarJogo();
		//caso queira ver o mapa de bombas, basta chamar imprimirTela com parametro true
	}
}
