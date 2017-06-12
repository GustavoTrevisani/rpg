package br.com.rpg.player;

import java.util.Scanner;

public class Player {

	protected Scanner read = new Scanner(System.in);

	protected String name;
	protected Integer level = 0;
	protected double life = 0;
	protected Integer strength = 0;
	protected String action = "";

	public void setName() {
		this.name = read.next();
	}

	public String getAction() {
		return this.action = read.next();
	}

	public void rest() {
		if (this.life < this.level + 10) {
			this.life = this.life + 1;
			System.out.println("Você restaurou 1 ponto de vida, agora você possui " + this.life + " pontos de vida!");
		} else {
			System.out.println("Você já está totalmente restaurado!");
		}
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void createPlayer(Integer level) {
		this.level = level;
		this.life = level + 10;
		this.strength = level + 1;
	}

	public Float attack() {
		return (float) this.strength;

	}

	public void status() {
		System.out.println("||||||||||||||\nNome:" + this.name + "\n" + "Level:" + this.level + "\n" + "Vida:"
				+ this.life + "\n||||||||||||||");
	}

	public String getName() {
		return this.name;
	}

	public Integer getLevel() {
		return this.level;
	}

	public double getLife() {
		return this.life;
	}

	public void takeDamage(Float targetAttack) {
		this.life = this.life - targetAttack;
	}
}
