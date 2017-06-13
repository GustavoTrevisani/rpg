package br.com.rpg.player;

import java.util.Scanner;
import javax.swing.JOptionPane;
import br.com.rpg.monster.Monster;
import br.com.rpg.tool.*;

public class Player {

	protected Scanner read = new Scanner(System.in);
	public String name;
	protected Integer level = 0;
	protected double life = 0;
	protected double maxlife = 0;
	protected double strength = 0;
	protected double maxstrength = 0;
	protected Integer dexterity = 0;
	protected Integer maxdexterity = 0;
	protected String action = "";
	protected double attack = 0;
	protected double souls = 1;

	public void createPlayer(Integer level) {
		setName();
		this.level = level;
		this.maxlife = level + 9;
		this.life = this.maxlife;
		this.maxstrength = level;
		this.strength = maxstrength;
		this.maxdexterity = level;
		this.dexterity = this.maxdexterity;
	}

	public void upgradeStatus(String atributo) {
		int x = 0;
		String stat = atributo;
		while (x ==0) {

			if (stat.equalsIgnoreCase("life")) {
				if (this.souls > (this.level * 2)) {
					this.maxlife++;
					this.life = this.maxlife;
					this.level++;
					break;
				} else {
					Tool.dialog("Negado", "Você não tem souls o suficiente para isto", 0);
					break;
				}
			} else if (stat.equalsIgnoreCase("strength")) {
				if (this.souls > (this.level * 2)) {
					this.maxstrength++;
					this.strength = this.maxstrength;
					this.level++;
					break;
				} else {
					Tool.dialog("Negado", "Você não tem souls o suficiente para isto", 0);
					break;
				}
			} else if (stat.equalsIgnoreCase("dexterity")) {
				if (this.souls > (this.level * 2)) {
					this.maxdexterity++;
					this.dexterity = this.maxdexterity;
					this.level++;
					break;
				} else {
					Tool.dialog("Negado", "Você não tem souls o suficiente para isto", 0);
					break;
				}
			} else {
				Tool.dialog("Atributo Inválido", "Você não digitou corretamente...", 3);
				stat = Tool.inputDialog("Teste", "Atributo", 3);
			}
		}
		x = 0;
	}

	public void setName() {
		this.name = Tool.inputDialog("Criando Personagem", "Qual o seu nome?", 3);

	}

	public void status() {
		Tool.dialog("Status do Personagem", "Nome:" + this.name + "\n" + "Level:" + this.level + "\n" + "Vida:"
				+ this.life + "\n" + "Força:" + this.strength + "\n" + "Dextreza:" + this.dexterity, 1);
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void rest() {
		if (this.life < this.maxlife) {
			this.life++;
			Tool.dialog("Restaurando vida",
					"Você restaurou 1 ponto de vida, agora você possui " + this.life + " pontos de vida!", 0);
		} else {
			System.out.println("Você já está totalmente restaurado!");
		}
	}

	public void attack(Monster target) {
		this.attack = (this.strength * (Tool.random(20) / 20.0));
		for (int i = 1; i <= this.dexterity; i++) {
			if (this.attack > this.strength / 2) {
				break;
			} else {
				this.attack = 0;
			}
		}
		if (this.attack > 0) {
			if (this.attack == this.strength) {
				Tool.dialog("Dano Máximo!", "Você acertou um golpe crítico!!!", 2);
			} else {
				Tool.dialog("Causou um golpe!.",
						"Você acertou o inimigo. O inimigo perdeu " + this.attack + " pontos de vida."
								+ " Agora ele possui " + (target.getLife() - this.attack) + " pontos de vida",
						2);
			}
		} else {
			Tool.dialog("Errou", "Você não acertou o inimigo...", 2);
		}
	}

	public void takeDamage(double targetAttack) {
		this.life = this.life - targetAttack;
	}

	public String getAction() {
		return this.action = Tool.inputDialog("teste", "teste", 0);
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

	public double getAttack() {
		return this.attack;
	}
}
