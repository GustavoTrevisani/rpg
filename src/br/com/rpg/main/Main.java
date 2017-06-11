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
		

		player.createPlayer(1);;
		monster.createMonster(player.level);
		
		System.out.println("Qual seu nome?");
		player.setName();
		delay.delay(1000);
		System.out.println("Bem vindo " + player.name + "!\nEstes são seus atributos iniciais...");
		delay.delay(1000);
		player.status();
		player.life = player.life -5;
		
		while (player.isAlive() && !exit) {
			System.out.println("O que você deseja fazer?\n|||Ações Permitidas|||"
					+ "\nExplorar um calabouço (e)\nSair do Jogo (esc)");
			player.action();
			if(player.action.equals("esc"))break;
				
		
			
									
			if (rn.random() > 15) {
				enemyOn = true;
			} else {
				enemyOn = false;
			}
			System.out.println(enemyOn);
			System.out.println("Para atacar digite (a)\npara recuperar sua vida digite (r)");
			player.action();
			if (player.action.equals("r")) {
				player.rest();

			}
			
		}
		System.out.println("Saiu");
	}
}