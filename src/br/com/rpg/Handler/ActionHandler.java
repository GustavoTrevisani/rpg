package br.com.rpg.Handler;

import java.util.logging.Handler;

import br.com.rpg.battle.Battle;
import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.tool.Tool;

public class ActionHandler {
		
	protected static String action = "";
	
	public static void runAction(Player hero, Monster enemy){
		action = hero.getAction();
		if(action.equalsIgnoreCase("Descansar")){
			hero.rest();
		}
		if(action.equalsIgnoreCase("Atacar")){
			hero.attack(enemy);
			enemy.takeDamage(hero.getAttack());
		}
		if(action.equalsIgnoreCase("Fugir")){
			if(Tool.random(20)>10){
			Battle.setBattleOn(false);
			Tool.dialog("Covarde", "Você fugiu correndo", 2);
			}else{
				Tool.dialog("Não dessa vez", "Você tentou fugir, mas foi agarrado pela perna!", 2);
			}
		}
		if(action.equalsIgnoreCase("Upgrade")){
			hero.upgradeStatus();
		}
		if(action.equalsIgnoreCase("Enemy Status")){
			enemy.status();
			ActionHandler.runAction(hero, enemy);
		}
		if(action.equalsIgnoreCase("Hero Status")){
			hero.status();
			ActionHandler.runAction(hero, enemy);
		}
		if(action.equalsIgnoreCase("Explorar Dungeon")){
			Battle.setBattleOn(true);
			enemy.createMonster(hero.getLevel());
			Battle.Batalha(hero, enemy);
		}
	}

}
