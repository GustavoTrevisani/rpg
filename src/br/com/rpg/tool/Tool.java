package br.com.rpg.tool;

import java.util.Random;

public class Tool {

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

}
