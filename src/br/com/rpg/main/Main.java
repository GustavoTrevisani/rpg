package br.com.rpg.main;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.tool.Tool;
import br.com.rpg.battle.*;
import br.com.rpg.gui.Botão;

public class Main {

	static Player hero = new Player();
	static Monster enemy = new Monster();

	public static void main(String[] args) {

		
		hero.createPlayer(5); // criando status do player com o level "1"
		enemy.createMonster(hero.getLevel());
		
		hero.status();
		hero.upgradeStatus();
		hero.status();

	}
}