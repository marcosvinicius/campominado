package br.com.poli.campominado.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.poli.campominado.*;
import br.com.poli.campominado.mapa.*;
import br.com.poli.campominado.gui.*;
import br.com.poli.campominado.jogo.CampoMinado;
import br.com.poli.campominado.jogo.Dificuldade;
import br.com.poli.campominado.jogo.Jogador;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Toolkit;


public class TelaMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Jogador jogador;
	
	public TelaMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMenu.class.getResource("/br/com/poli/campominado/midia/1.png")));
		setResizable(false);
		setTitle("Campo Minado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCampoMinado = new JLabel("");
		lblCampoMinado.setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/2.png")));
		lblCampoMinado.setHorizontalAlignment(SwingConstants.CENTER);
		lblCampoMinado.setFont(new Font("Arial", Font.BOLD, 30));
		lblCampoMinado.setBounds(186, 110, 390, 70);
		contentPane.add(lblCampoMinado);
		
		JLabel lblNome = new JLabel("");
		lblNome.setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/nome.png")));
		lblNome.setFont(new Font("Arial", Font.BOLD, 15));
		lblNome.setBounds(217, 222, 84, 17);
		contentPane.add(lblNome);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setBounds(419, 219, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDificuldade = new JLabel("");
		lblDificuldade.setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/dificuldade.png")));
		lblDificuldade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDificuldade.setFont(new Font("Arial", Font.BOLD, 15));
		lblDificuldade.setBounds(217, 250, 163, 17);
		contentPane.add(lblDificuldade);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(Dificuldade.values()));
		comboBox.setBounds(419, 247, 111, 20);
		contentPane.add(comboBox);
		
		JLabel lblColoqueSeuNome = new JLabel("Coloque seu nome!");
		lblColoqueSeuNome.setForeground(Color.RED);
		lblColoqueSeuNome.setFont(new Font("Arial", Font.BOLD, 11));
		lblColoqueSeuNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblColoqueSeuNome.setBounds(540, 222, 119, 14);
		contentPane.add(lblColoqueSeuNome);
		lblColoqueSeuNome.setVisible(false);
		
		JButton btnIniciar = new JButton("");
		btnIniciar.setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/iniciar.png")));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().isEmpty()) {
					lblColoqueSeuNome.setVisible(true);
				}
				else {
					dispose();
					jogador = new Jogador(textField.getText());
					if (comboBox.getSelectedIndex() == 0) {
						CampoMinado campo = new CampoMinado(jogador, Dificuldade.FACIL);
						TelaJogo telaJogo = new TelaJogo(campo);
						telaJogo.setVisible(true);
					}
					else if (comboBox.getSelectedIndex() == 1) {
						CampoMinado campo = new CampoMinado(jogador, Dificuldade.MEDIO);
						TelaJogo telaJogo = new TelaJogo(campo);
						telaJogo.setVisible(true);
						
					}
					else {
						CampoMinado campo = new CampoMinado(jogador, Dificuldade.DIFICIL);
						TelaJogo telaJogo = new TelaJogo(campo);
						telaJogo.setVisible(true);
					}
				}
			}
		});
		btnIniciar.setFont(new Font("Arial", Font.BOLD, 15));
		btnIniciar.setBounds(330, 290, 111, 23);
		contentPane.add(btnIniciar);
		
		JButton btnRanking = new JButton("");
		btnRanking.setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/ranking.png")));
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				TelaRanking telaRanking = new TelaRanking();
				telaRanking.setVisible(true);
			}
		});
		btnRanking.setFont(new Font("Arial", Font.BOLD, 15));
		btnRanking.setBounds(330, 315, 111, 23);
		contentPane.add(btnRanking);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(TelaMenu.class.getResource("/br/com/poli/campominado/midia/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setFont(new Font("Arial", Font.BOLD, 15));
		btnSair.setBounds(330, 340, 111, 23);
		contentPane.add(btnSair);
	}
}
