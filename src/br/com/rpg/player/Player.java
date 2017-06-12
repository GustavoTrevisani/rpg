package br.com.rpg.player;

import java.util.Scanner;
import br.com.rpg.tool.*;

public class Player {

	protected Scanner read = new Scanner(System.in);
	protected String name;
	protected Integer level = 0;
	protected double life = 0;
	protected Integer strength = 0;
	protected Integer dexterity = 0;
	protected String action = "";
	protected double attack = 0;

	public void createPlayer(Integer level) {
		this.level = level;
		this.life = level + 9;
		this.strength = level;
		this.dexterity = level;
	}

	public void setName() {
		this.name = read.next();
	}

	public void status() {
		System.out.println("||||||||||||||\nNome:" + this.name + "\n" + "Level:" + this.level + "\n" + "Vida:"
				+ this.life + "\n||||||||||||||");
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
				Tool.print("Você acertou um golpe crítico!!!");
			}else{
				Tool.print("Você acertou o inimigo...");
			}
		}else{
			Tool.print("Você não acertou o inimigo...");
		}
		
		return this.attack;
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

	public void takeDamage(double targetAttack) {
		this.life = this.life - targetAttack;
	}
	public double getAttack(){
		return this.attack;
	}
}
