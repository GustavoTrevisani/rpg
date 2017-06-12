package br.com.rpg.main;

import java.util.Scanner;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.tool.*;

public class Main {
	static Scanner read = new Scanner(System.in);
	static Player player = new Player();
	static Monster monster = new Monster();
	static Tool delay = new Tool();
	static Tool rn = new Tool();
	static boolean enemyOn = false;
	static boolean exit = false;

	public static void main(String[] args) {

		player.createPlayer(1);
		;
		monster.createMonster(player.getLevel());

		System.out.println("Qual seu nome?");
		player.setName();
		delay.delay(1000);
		System.out.println("Bem vindo " + player.getName() + "!\nEstes são seus atributos iniciais...");
		delay.delay(1000);
		player.status();
		player.takeDamage(monster.attack());
		player.status();
		
		
	}
}