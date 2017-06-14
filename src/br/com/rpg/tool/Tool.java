package br.com.rpg.tool;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.rpg.player.Player;

public class Tool {

	public static String[] opções = { "a", "b" };

	public static void delay(Integer x) {
		try {
			Thread.sleep(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Integer random(Integer dice) {
		Random randnumber = new Random();
		return randnumber.nextInt(dice);
	}

	public static void print(String message) {
		System.out.println(message);
	}

	public static void dialog(String title, String message, int icon) {
		JOptionPane.showMessageDialog(null, message, title, icon);
	}

	public static String inputDialog(String title, String message, int icon) {
		return (String) JOptionPane.showInputDialog(null, message, title, icon);
	}

	public static Integer inputDialogOptions(String title, String message, String[] options){
		return  JOptionPane.showOptionDialog(null, message, title, 0, 0, null, options, "teste");
	}
}
