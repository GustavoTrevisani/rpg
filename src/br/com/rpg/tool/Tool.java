package br.com.rpg.tool;

import java.util.Random;

public class Tool {

	public void delay(Integer x) {
		try {
			Thread.sleep(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer random(Integer dice) {
		Random randnumber = new Random();
		return randnumber.nextInt(dice);
	}
	
	public void print(String message){
		System.out.println(message);
	}
}
