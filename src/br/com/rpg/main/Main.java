package br.com.rpg.main;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.tool.Tool;
import br.com.rpg.battle.*;
import br.com.rpg.gui.Bot�o;

public class Main {

	static Bot�o bot = new Bot�o();

	static Player hero = new Player();
	static Monster enemy = new Monster();

	public static void main(String[] args) {

		hero.createPlayer(1); // criando status do player com o level "1"
		hero.upgradeStatus(Tool.inputDialog("Teste", "Atributo", 3));
		enemy.createMonster(hero.getLevel());
		Battle.batalha(hero, enemy);


	}
}