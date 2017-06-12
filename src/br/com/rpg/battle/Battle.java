package br.com.rpg.battle;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.*;
import br.com.rpg.tool.Tool;

public class Battle {

	public static void batalha(Player player, Monster monster) {
		Tool.print("Você encontrou um inimigo.");
		player.status();
		monster.status();
		player.attack(monster);
		monster.takeDamage(player.getAttack());
		monster.attack(player);
		player.takeDamage(monster.getAttack());
		player.status();
		monster.status();
	}

}
