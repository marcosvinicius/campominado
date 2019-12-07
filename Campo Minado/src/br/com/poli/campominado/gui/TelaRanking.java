package br.com.poli.campominado.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.poli.campominado.jogo.*;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaRanking extends JFrame {

	private JPanel contentPane;

	public TelaRanking() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRanking.class.getResource("/br/com/poli/campominado/midia/1.png")));
		setResizable(false);
		setTitle("Campo Minado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Arial", Font.PLAIN, 12));
		textPane.setEditable(false);
		textPane.setBounds(10, 48, 774, 512);
		contentPane.add(textPane);
		
		JLabel lblRanking = new JLabel("");
		lblRanking.setIcon(new ImageIcon(TelaRanking.class.getResource("/br/com/poli/campominado/midia/rankingMaior.png")));
		lblRanking.setFont(new Font("Arial", Font.BOLD, 30));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setBounds(300, 0, 178, 49);
		contentPane.add(lblRanking);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(TelaRanking.class.getResource("/br/com/poli/campominado/midia/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TelaMenu telaMenu = new TelaMenu();
				telaMenu.setVisible(true);
			}
		});
		btnSair.setFont(new Font("Arial", Font.BOLD, 15));
		btnSair.setBounds(695, 14, 89, 23);
		contentPane.add(btnSair);
		
		try {
			FileReader arq = new FileReader("Ranking.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			
			String linha = "";
			String total = "";
			while ((linha = lerArq.readLine()) != null) {		
				total = total + "\n" + linha;
			}
			textPane.setText(total);
			lerArq.close();
		} catch (IOException e) {
			e.printStackTrace(); //aqui é responsavel por ler o txt e colocar no textPane
		}
	}
	
	public void gravarRanking(CampoMinado campo) {
		try {
			FileWriter arq = new FileWriter("Ranking.txt", true);
			BufferedWriter gravarArq = new BufferedWriter(arq);
			
			gravarArq.newLine();
			gravarArq.write("Nome: " + campo.getJogador().getNome());
			gravarArq.newLine();
			gravarArq.write("Dificuldade: " + campo.getDificuldade().toString());
			gravarArq.newLine();
			gravarArq.write("Tempo: " + campo.getJogador().getTempo() + "s"); //responsavel por gravar no ranking
			
			gravarArq.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
