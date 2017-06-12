package br.com.rpg.main;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.battle.*;

public class Main {

	static Player player = new Player();
	static Monster monster = new Monster();

	public static void main(String[] args) {

		player.createPlayer(1); // criando status do player com o level "1"
		monster.createMonster(player.getLevel());

		Battle.batalha(player, monster);

	}
}