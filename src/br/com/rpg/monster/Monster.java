package br.com.rpg.monster;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import br.com.rpg.player.Player;
import br.com.rpg.tool.*;

public class Monster {

	protected Integer level = 0;
	protected float life = 0;
	protected float strength = 0;
	protected float attack = 0;
	protected Integer dexterity = 0;
	protected Integer souls = 0;
	protected String damage = "";
	protected DecimalFormat decimal = new DecimalFormat("0.00");

	public void createMonster(Integer level) {
		this.level = level;
		this.life = this.level + 9 - 1;
		this.strength = this.level + 1 - 1;
		this.dexterity = this.level + 1 - 1;
		this.souls = this.level;

	}

	public void attack(Player target) {
		for (int i = 1; i <= this.dexterity; i++) {
			this.attack = (this.strength * (Tool.random(20).floatValue() / 20));
			if (this.attack > this.strength / 2) {
				damage = decimal.format(target.getLife() - this.attack);

				break;
			} else {
				this.attack = 0;
			}
		}
		if ((target.getLife()-this.attack) > 0) {
			if (this.attack > 0) {
				if (this.attack == this.strength) {
					Tool.dialog("Dano Máximo!", "Você recebeu um golpe crítico!!!", 2);
					target.takeDamage(getAttack());
				} else {
					Tool.dialog("Recebeu um golpe!.", "O inimigo acertou você. Você perdeu " + this.attack
							+ " pontos de vida." + " Agora você possui " + damage + " pontos de vida", 2);
					target.takeDamage(getAttack());
				}
			} else {
				Tool.dialog("Escapou", "O inimigo não acertou você...", 2);
			}
		} else {
			Tool.dialog("Perdeu", "Você perdeu " + this.attack + " pontos de vida. Você foi derrotado!", 2);
			target.takeDamage(getAttack());
		}
	}

	public boolean isAlive() {
		return (this.life > 0.0);
	}

	public void status() {
		System.out.println("||||||||||||||\n" + "---Inimigo---\n" + "Level:" + this.level + "\n" + "Vida:" + this.life
				+ "\n||||||||||||||");
	}

	public void takeDamage(float targetAttack) {
		this.life = this.life - targetAttack;
		if (this.life < 0.0) {
			this.life = 0;
		}
	}

	public float getLife() {
		return this.life;
	}

	public float getAttack() {
		return this.attack;
	}
}