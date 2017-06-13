package br.com.rpg.gui;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class ButtonHandler implements ActionListener {
	private JButton bt1, bt2;

	public ButtonHandler(JButton botão1, JButton botão2) {
		this.bt1 = botão1;
		this.bt2 = botão2;
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == bt1)
			JOptionPane.showMessageDialog(null, "Você atacou!");

		if (evento.getSource() == bt2)
			JOptionPane.showMessageDialog(null, "você defendeu");
	}

}
