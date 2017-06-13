package br.com.rpg.main;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.tool.Tool;
import br.com.rpg.battle.*;
import br.com.rpg.gui.Botão;

public class Main {

	static Botão bot = new Botão();

	static Player player = new Player();
	static Monster monster = new Monster();

	public static void main(String[] args) {

		player.createPlayer(1); // criando status do player com o level "1"
		player.upgradeStatus(Tool.inputDialog("Teste", "Atributo", 3));
		monster.createMonster(player.getLevel());
		Battle.batalha(player, monster);

		/*
		 * bot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); bot.setSize(350,
		 * 70); bot.setLocationRelativeTo(null); bot.setVisible(true);
		 */
		//Battle.batalha(player, monster);

	}
}