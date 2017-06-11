package br.com.rpg.player;

import java.util.Scanner;

import br.com.rpg.monster.Monster;

public class Player {

	protected Scanner read = new Scanner(System.in);

	public String name;
	public Integer level = 0;
	public double life = 0;
	public Integer strength = 0;
	public String action = "";

	public void setName() {
		this.name = read.next();
	}

	public void action() {
		this.action = read.next();
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

	public void attack(Monster target) {
		target.life = target.life - this.strength;

	}

	public void status() {
		System.out.println("||||||||||||||\nNome:" + this.name + "\n" + "Level:" + this.level + "\n" + "Vida:"
				+ this.life + "\n||||||||||||||");
	}
}
