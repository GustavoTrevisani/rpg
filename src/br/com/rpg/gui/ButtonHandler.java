package br.com.rpg.gui;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class ButtonHandler implements ActionListener {
	private JButton bt1, bt2;

	public ButtonHandler(JButton bot�o1, JButton bot�o2) {
		this.bt1 = bot�o1;
		this.bt2 = bot�o2;
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == bt1)
			JOptionPane.showMessageDialog(null, "Voc� atacou!");

		if (evento.getSource() == bt2)
			JOptionPane.showMessageDialog(null, "voc� defendeu");
	}

}
