package br.com.rpg.monster;

import java.text.DecimalFormat;
import br.com.rpg.player.Player;
import br.com.rpg.tool.*;

public class Monster {

	protected Integer level = 0;
	protected float life = 0;
	protected Integer strength = 0;
	protected float attack = 0;
	protected Integer dexterity = 0;
	protected Integer souls = 0;
	DecimalFormat df = new DecimalFormat("#.##");

	public void createMonster(Integer level) {
		this.level = level;
		this.life = this.level + 9;
		this.strength = this.level;
		this.dexterity = this.level;
		this.souls = this.level;

	}

	public void attack(Player target) {
		for (int i = 1; i <= this.dexterity; i++) {
			this.attack = (float) (this.strength * (Tool.random(20) / 20.0));
			if (this.attack >= (this.strength / 2.0)) {
				break;
			} else {
				this.attack = 0;
			}
		}
		if ((target.getLife() - this.attack) > 0) {
			if (this.attack > 0) {
				if (this.attack == this.strength) {
					Tool.dialog("Dano Máximo!", "Você recebeu um golpe crítico!!!", 2);
					target.takeDamage(getAttack());
				} else {
					Tool.dialog("Recebeu um golpe!.",
							"O inimigo acertou você. Você perdeu " + this.attack + " pontos de vida."
									+ " Agora você possui " + df.format(target.getLife() - this.attack)
									+ " pontos de vida",
							2);
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
		return (this.life > 0);
	}

	public void status() {
		Tool.dialog("Status do Inimigo",
				"---Inimigo---\n" + "Level: " + this.level + "\n" + "Vida: " + df.format(this.life), 1);
	}

	public void takeDamage(float targetAttack) {
		this.life = this.life - targetAttack;
		if (this.life < 0) {
			this.life = 0;
		}
	}

	public float getLife() {
		return this.life;
	}

	public Integer getSouls() {
		return this.souls;
	}

	public float getAttack() {
		return this.attack;
	}
}