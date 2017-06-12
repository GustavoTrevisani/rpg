package br.com.rpg.monster;

public class Monster {

	protected Integer level = 0;
	protected double life = 0;
	protected Integer strength = 0;

	public void createMonster(Integer level) {
		this.level = level - 1;
		this.life = this.level + 10;
		this.strength = this.level + 1;

	}

	public Float attack() {
		return (float) this.strength;
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void status() {
		System.out
				.println("||||||||||||||\n" + "Level:" + this.level + "\n" + "Vida:" + this.life + "\n||||||||||||||");
	}
}