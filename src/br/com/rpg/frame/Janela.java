package br.com.rpg.frame;

import javax.swing.JFrame;
import java.awt.Button;

public class Janela {

	static Button bot�o = new Button();
	static JFrame janela = new JFrame();

	public static void window() {
		bot�o.setSize(100,100);
		bot�o.setLocation(0,0);
		bot�o.setName("Player!");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(500, 500);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setAlwaysOnTop(true);
		janela.setLayout(null);
		
		janela.add(bot�o);

	}

}
