package br.com.rpg.monster;

import java.text.DecimalFormat;
import java.util.Random;

import br.com.rpg.player.Player;
import br.com.rpg.tool.*;

public class Monster {

	protected String name = "";
	protected Integer level = 0;
	protected float life = 0;
	protected Integer strength = 0;
	protected float attack = 0;
	protected Integer dexterity = 0;
	protected Integer souls = 0;
	DecimalFormat formated_float = new DecimalFormat("#.##");
	Random randnumber = new Random();

	public void createMonster(Player hero) {
		this.name = generateName();
		while (this.level == 0 || this.level < hero.getLevel() - 5) {
			this.level = Tool.random(hero.getLevel()+1);
		}
		while (this.life <= hero.getMaxLife() / 2.0 || this.life > hero.getMaxLife() * 0.90) {
			this.life = (Tool.random(hero.getLevel()+9));
		}
		if (hero.getLevel() != 1) {
			while (this.strength < hero.getMaxStrength() - 5 || this.strength > hero.getMaxStrength() * 0.90) {
				this.strength = Tool.random(hero.getLevel()+1);
			}

			while (this.dexterity < hero.getMaxDexterity() - 5 || this.dexterity > hero.getMaxDexterity() * 0.90) {
				this.dexterity = Tool.random(hero.getLevel()+1);
			}
		} else {
			this.strength = hero.getMaxStrength();
			this.dexterity = hero.getMaxDexterity();
		}
		this.souls = this.level;
	}

	public String generateName() {
		String[] consoantes = { "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v",
				"x", "z" };
		String[] vogais = { "a", "e", "i", "o", "u" };
		String generated_name = "";
		Integer name_max_length = Tool.random(5);
		generated_name = generated_name + consoantes[Tool.random(19)];
		generated_name = generated_name.toUpperCase();
		for (int i = 0; i < name_max_length; i++) {
			generated_name = generated_name + vogais[Tool.random(5)];
			generated_name = generated_name + consoantes[Tool.random(19)];

		}
		return generated_name;

	}

	public void attack(Player target) {
		for (int i = 1; i <= this.dexterity; i++) {
			this.attack = (float) (this.strength * (Tool.random(21) / 20.0));
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
									+ " Agora você possui " + formated_float.format(target.getLife() - this.attack)
									+ " pontos de vida",
							2);
					target.takeDamage(getAttack());
				}
			} else {
				Tool.dialog("Escapou", "O inimigo não acertou você...", 2);
			}
		} else {
			Tool.dialog("Derrota!",
					"Você perdeu " + this.attack + " pontos de vida.\nVocê teve sua cabeça arrancada do seus ombros!",
					2);
			target.takeDamage(getAttack());
		}
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void status() {
		Tool.dialog("Status do Inimigo",
				"Nome: " + this.name + "\nLevel: " + this.level + "\nVida: " + formated_float.format(this.life)
						+ "\nForça: " + this.strength + "\nDestreza: " + this.dexterity + "\nSouls: " + this.souls,
				1);
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