package br.com.rpg.battle;

import br.com.rpg.monster.Monster;
import br.com.rpg.player.*;
import br.com.rpg.tool.Tool;

public class Battle {

	public static void batalha(Player hero, Monster enemy) {
		Tool.print("Voc� encontrou um inimigo.");
		 hero.status();
		 enemy.status();
		while (hero.isAlive() && enemy.isAlive()) {
			hero.attack(enemy);
			enemy.takeDamage(hero.getAttack());
			if (enemy.isAlive()) {
				enemy.attack(hero);
				hero.takeDamage(enemy.getAttack());
			} else {
				break;
			}
		}
	}
}
