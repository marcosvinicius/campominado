package br.com.poli.campominado.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.poli.campominado.*;
import br.com.poli.campominado.gui.*;
import br.com.poli.campominado.jogo.*;
import br.com.poli.campominado.mapa.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class TelaJogo extends JFrame {

	private JPanel contentPane;
	private CampoMinado campo;
	private Botao[][] botoes;
	private Botao botaoEscolhido;
	private int segundos = 0;
	private JLabel labelSegundos;
	private JLabel labelQtdBandeiras;
	private Timer timer = new Timer();
	private boolean rodando;
	private int qtdBandeiras;
	
	public TelaJogo(CampoMinado campo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaJogo.class.getResource("/br/com/poli/campominado/midia/1.png")));
		setForeground(Color.WHITE);
		this.campo = campo;
		this.qtdBandeiras = campo.getMapa().getBomba();
		setTitle("Campo Minado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblJogador = new JLabel("");
		lblJogador.setIcon(new ImageIcon(TelaJogo.class.getResource("/br/com/poli/campominado/midia/jogador.png")));
		lblJogador.setFont(new Font("Arial", Font.BOLD, 15));
		lblJogador.setBounds(10, 11, 89, 18);
		contentPane.add(lblJogador);
		
		JLabel lblNomeJogador = new JLabel("");
		lblNomeJogador.setFont(new Font("Arial", Font.BOLD, 15));
		lblNomeJogador.setBounds(109, 11, 72, 18);
		contentPane.add(lblNomeJogador);
		lblNomeJogador.setText(campo.getJogador().getNome());
		
		JLabel lblDificuldade = new JLabel("");
		lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificuldade.setIcon(new ImageIcon(TelaJogo.class.getResource("/br/com/poli/campominado/midia/dificuldadeMenor.png")));
		lblDificuldade.setFont(new Font("Arial", Font.BOLD, 15));
		lblDificuldade.setBounds(179, 11, 124, 18);
		contentPane.add(lblDificuldade);
		
		JLabel lblNomeDificuldade = new JLabel("");
		lblNomeDificuldade.setFont(new Font("Arial", Font.BOLD, 15));
		lblNomeDificuldade.setBounds(308, 11, 72, 18);
		contentPane.add(lblNomeDificuldade);
		lblNomeDificuldade.setText(campo.getDificuldade().toString());
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);
		painelBotoes.setBounds(10, 40, 774, 520);
		painelBotoes.setLayout(new GridLayout(campo.getDificuldade().getValorDificuldade(), campo.getDificuldade().getValorDificuldade()));
		contentPane.add(painelBotoes);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(TelaJogo.class.getResource("/br/com/poli/campominado/midia/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TelaMenu telaMenu = new TelaMenu();
				telaMenu.setVisible(true);
			}
		});
		btnSair.setFont(new Font("Arial", Font.BOLD, 15));
		btnSair.setBounds(695, 11, 89, 21);
		contentPane.add(btnSair);
		
		JLabel lblTempo = new JLabel("");
		lblTempo.setIcon(new ImageIcon(TelaJogo.class.getResource("/br/com/poli/campominado/midia/tempo.png")));
		lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTempo.setFont(new Font("Arial", Font.BOLD, 15));
		lblTempo.setBounds(382, 11, 65, 18);
		contentPane.add(lblTempo);
		
		JLabel lblSegundos = new JLabel("0");
		labelSegundos = lblSegundos;
		lblSegundos.setFont(new Font("Arial", Font.BOLD, 15));
		lblSegundos.setBounds(457, 13, 46, 14);
		contentPane.add(lblSegundos);
		
		JLabel lblBandeiras = new JLabel("");
		lblBandeiras.setHorizontalAlignment(SwingConstants.CENTER);
		lblBandeiras.setIcon(new ImageIcon(TelaJogo.class.getResource("/br/com/poli/campominado/midia/bandeiras.png")));
		lblBandeiras.setFont(new Font("Arial", Font.BOLD, 15));
		lblBandeiras.setBounds(502, 11, 114, 18);
		contentPane.add(lblBandeiras);
		
		JLabel lblQtdBandeiras = new JLabel("0");
		labelQtdBandeiras = lblQtdBandeiras;
		lblQtdBandeiras.setFont(new Font("Arial", Font.BOLD, 15));
		lblQtdBandeiras.setBounds(626, 11, 46, 18);
		contentPane.add(lblQtdBandeiras);
		lblQtdBandeiras.setText(Integer.toString(qtdBandeiras));
		
		botoes = new Botao[campo.getDificuldade().getValorDificuldade()][campo.getDificuldade().getValorDificuldade()];
		
		for (int i = 0; i < campo.getDificuldade().getValorDificuldade(); i++) {
			for (int j = 0; j < campo.getDificuldade().getValorDificuldade(); j++) {
				botoes[i][j] = new Botao(i,j);				
				botoes[i][j].setMargin(new Insets(0,0,0,0));
				botoes[i][j].setForeground(Color.RED);
				botoes[i][j].setBackground(Color.DARK_GRAY);
				botoes[i][j].addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						botaoEscolhido = (Botao) e.getSource();
						campo.getMapa().escolherPosicao(botaoEscolhido.getLinha(), botaoEscolhido.getColuna());
						botaoEscolhido.setEnabled(false);
						atualizarMapa();
						conferirJogo();
						if (rodando == false) {
							iniciarCronometro();
							rodando = true;
						}				
					}
				});
				painelBotoes.add(botoes[i][j]); //aqui vai pegar qual botao foi clicado
				
				botoes[i][j].addMouseListener(new MouseAdapter(){
				      public void mousePressed(MouseEvent e){
				    	  botaoEscolhido = (Botao) e.getSource();
				    	  if(e.getButton() == MouseEvent.BUTTON3){
				    		  if (botaoEscolhido.getText().equals("|>")) {			    			  
				    			  botaoEscolhido.setText("");				    			  				
				    			  qtdBandeiras++;				    	
				    		  }
				    		  else if (qtdBandeiras > 0 && botaoEscolhido.getText().equals("")) {
				    			  botaoEscolhido = (Botao) e.getSource();
				    			  botaoEscolhido.setText("|>");
				    			  qtdBandeiras--;				    			  
				    		  }				    		  
				    		  lblQtdBandeiras.setText(Integer.toString(qtdBandeiras));//esse metodo pega o click direito do mouse para colocar a bandeira		    				    		  
				    	  }
				       }
				});
			}
		}
		
		
	}
	
	public void atualizarMapa() {
		for (int i = 0; i < campo.getDificuldade().getValorDificuldade(); i++) {
			for (int j = 0; j < campo.getDificuldade().getValorDificuldade(); j++) {
				if (campo.getMapa().getCelula(i, j).getQtdBombasVizinhas() == 0 && campo.getMapa().getCelula(i, j).isVisivel()) {
					if(botoes[i][j].getText().equals("|>")) {
						botoes[i][j].setText("");
						qtdBandeiras++;
						labelQtdBandeiras.setText(Integer.toString(qtdBandeiras));
					}
					botoes[i][j].setEnabled(false);
					botoes[i][j].setBackground(Color.LIGHT_GRAY);
				}
				else if (campo.getMapa().getCelula(i, j).getQtdBombasVizinhas() != 0 && campo.getMapa().getCelula(i, j).isVisivel()) {
					if(botoes[i][j].getText().equals("|>")) {
						botoes[i][j].setText("");
						qtdBandeiras++;
						labelQtdBandeiras.setText(Integer.toString(qtdBandeiras));
					}
					if (campo.getMapa().getCelula(i, j).getQtdBombasVizinhas() == 1) {
						botoes[i][j].setBackground(Color.GREEN);
					}
					else if (campo.getMapa().getCelula(i, j).getQtdBombasVizinhas() == 2) {
						botoes[i][j].setBackground(Color.ORANGE);
					}
					else if (campo.getMapa().getCelula(i, j).getQtdBombasVizinhas() > 2) {
						botoes[i][j].setBackground(Color.RED);
					}
					botoes[i][j].setText(Integer.toString(campo.getMapa().getCelula(i, j).getQtdBombasVizinhas()));
					botoes[i][j].setEnabled(false); //esse metodo é o que expande os botoes
					//botoes[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}
	
	public void conferirJogo() {
		if (campo.getMapa().isFimDeJogo()) {
			timer.cancel();
			for (int i = 0; i < botoes.length; i++) {
				for (int j = 0; j < botoes.length; j++) {
					if (campo.getMapa().getCelula(i, j).isBomba()) {
						botoes[i][j].setText("");
						botoes[i][j].setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/bombaPequena.png")));
					}
				}
			}
			JOptionPane.showMessageDialog(contentPane, "Você perdeu!");
			dispose();
			TelaMenu telaMenu = new TelaMenu();
			telaMenu.setVisible(true);
		}
		else if (campo.getMapa().isGanhouJogo()) {
			timer.cancel();
			campo.getJogador().setTempo(labelSegundos.getText());
			TelaRanking telaRanking = new TelaRanking();
			telaRanking.gravarRanking(campo);
			JOptionPane.showMessageDialog(contentPane, "Você ganhou, parabéns!"); //checa se ganhou o jogo, se ganhar, vai pro ranking
			dispose();
			TelaMenu telaMenu = new TelaMenu();
			telaMenu.setVisible(true);			
		}
	}
	
	public void iniciarCronometro() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				segundos++;		
				labelSegundos.setText(Integer.toString(segundos));			
			}
		}, 1000, 1000); //incrementa a variavel segundos de 1 em 1 segundo
		
	}
}
