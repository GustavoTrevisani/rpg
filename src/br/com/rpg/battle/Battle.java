package br.com.rpg.battle;

import br.com.rpg.Handler.ActionHandler;
import br.com.rpg.monster.Monster;
import br.com.rpg.player.*;
import br.com.rpg.tool.Tool;

public class Battle {

	protected static boolean battle_status;

	public static void Batalha(Player hero, Monster enemy) {
		Tool.dialog("Prepare-se", "Você encontrou um inimigo", 2);
		while (hero.isAlive() && enemy.isAlive() && battle_status) {
			ActionHandler.runAction(hero, enemy);
			if (enemy.isAlive() && battle_status) {
				enemy.attack(hero);
			} else {
				battle_status = false;
				break;
			}
		}
	}

	public static boolean isBattleOn() {
		return battle_status;
	}

	public static void setBattleOn(boolean set) {
		battle_status = set;

	}
}
