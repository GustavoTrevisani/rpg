package br.com.rpg.monster;

import br.com.rpg.tool.*;

public class Monster {

	protected Integer level = 0;
	protected double life = 0;
	protected Integer strength = 0;
	protected double attack = 0;
	protected Integer dexterity = 0;

	public void createMonster(Integer level) {
		this.level = level - 1;
		this.life = this.level + 9;
		this.strength = this.level + 1;
		this.dexterity = this.level + 1;

	}

	public double attack() {

		this.attack = (this.strength * (Tool.random(20) / 20.0));
		for (int i = 1; i <= this.dexterity; i++) {
			if (this.attack > this.strength / 2)
			{
				break;
			} else {
				this.attack = 0;
			}

		}
		if(this.attack > 0){
			if (this.attack == this.strength) {
				Tool.print("Você recebeu um golpe crítico!!!");
			}else{
				Tool.print("O inimigo acertou você...");
			}
		}else{
			Tool.print("O inimigo não acertou você...");
		}
		
		return this.attack;
	}


	public boolean isAlive() {
		return (this.life > 0);
	}

	public void status() {
		System.out
				.println("||||||||||||||\n" + "---Inimigo---\n" + "Level:" + this.level + "\n" + "Vida:" + this.life + "\n||||||||||||||");
	}

	public void takeDamage(double targetAttack) {
		this.life = this.life - targetAttack;
	}
	public double getAttack(){
		return this.attack;
	}

}