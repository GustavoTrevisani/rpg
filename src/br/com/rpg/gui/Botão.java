package br.com.rpg.gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Bot�o extends JFrame {
	private JButton attack = new JButton("Atacar");
	private JButton rest = new JButton("Descansar");
	private ButtonHandler handler;

	public Bot�o() {
		super("A��es permitidas");
		setLayout(new FlowLayout());
		handler = new ButtonHandler(attack, rest);

		attack.addActionListener(handler);
		add(attack);

		//defense.addActionListener(handler);
		add(rest);
	}
}
