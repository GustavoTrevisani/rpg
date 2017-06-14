package br.com.rpg.battle;

import br.com.rpg.Handler.ActionHandler;
import br.com.rpg.monster.Monster;
import br.com.rpg.player.*;
import br.com.rpg.tool.Tool;

public class Battle {

	protected static boolean battle_on;

	public static void Batalha(Player hero, Monster enemy) {
		Tool.dialog("Prepare-se", "Você encontrou um inimigo", 2);
		while (hero.isAlive() && enemy.isAlive()&& battle_on) {
			ActionHandler.runAction(hero,enemy);
			if (enemy.isAlive()&& battle_on) {
				enemy.attack(hero);
				hero.takeDamage(enemy.getAttack());
			} else {
				battle_on = false;
				break;
			}
		}
	}

	public static boolean isBattleOn() {
		return battle_on;
	}
	public static void setBattleOn(boolean set){
		battle_on = set;
		
	}
}
