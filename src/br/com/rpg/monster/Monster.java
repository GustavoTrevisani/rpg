package br.com.rpg.monster;

import br.com.rpg.player.Player;

public class Monster {

	public Integer level = 0;
	public double life = 0;
	public Integer strength = 0;

	public void createMonster(Integer level) {
		this.level = level - 1;
		this.life = this.level + 10;
		this.strength = this.level + 1;

	}

	public void attack(Player target) {
		target.life = target.life - this.strength;
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void status() {
		System.out.println("||||||||||||||\n" + "Level:" + this.level + "\n" + "Vida:" + this.life + "\n||||||||||||||");
	}
}