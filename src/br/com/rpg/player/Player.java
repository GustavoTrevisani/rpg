package br.com.rpg.player;

import java.text.DecimalFormat;
import java.util.Scanner;

import br.com.rpg.battle.Battle;
import br.com.rpg.monster.Monster;
import br.com.rpg.tool.*;

public class Player {

	protected Scanner read = new Scanner(System.in);
	public String name = "";
	protected Integer level = 0;
	protected float life = 0;
	protected float maxlife = 0;
	public float strength = 0;
	protected float maxstrength = 0;
	protected Integer dexterity = 0;
	protected Integer maxdexterity = 0;
	protected static String action = "";
	protected float attack = 0;
	protected Integer souls = 0;
	protected String damage = "";
	protected DecimalFormat decimal = new DecimalFormat("0.00");
	protected static String[] options = new String[10];

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

	public void upgradeStatus() {

		int x = 0;
		String stat = Tool.inputDialog("Upgrade de atributo", "Qual atributo, você deseja aprimorar?", 3);
		while (x == 0) {

			if (stat.equalsIgnoreCase("life")) {
				if (this.souls >= (this.level * 2)) {
					this.maxlife++;
					this.life = this.maxlife;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					break;
				} else {
					Tool.dialog("Negado", "Você não tem souls o suficiente para isto", 0);
					break;
				}
			} else if (stat.equalsIgnoreCase("strength")) {
				if (this.souls >= (this.level * 2)) {
					this.maxstrength++;
					this.strength = this.maxstrength;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					break;
				} else {
					Tool.dialog("Negado", "Você não tem souls o suficiente para isto", 0);
					break;
				}
			} else if (stat.equalsIgnoreCase("dexterity")) {
				if (this.souls >= (this.level * 2)) {
					this.maxdexterity++;
					this.dexterity = this.maxdexterity;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					break;
				} else {
					Tool.dialog("Negado", "Você não tem souls o suficiente para isto", 0);
					break;
				}
			} else {
				Tool.dialog("Atributo Inválido", "Você não digitou corretamente...", 3);
				stat = Tool.inputDialog("Upgrade de atributo", "Qual atributo, você deseja aprimorar?", 3);
			}
		}
		x = 0;
	}

	public void setName() {
		while (this.name.equals("")) {
			this.name = Tool.inputDialog("Criando Personagem", "Qual o seu nome?", 3);
		}

	}

	public void status() {
		Tool.dialog("Status do Personagem", "Nome:" + this.name + "\n" + "Level:" + this.level + "\n" + "Vida:"
				+ this.life + "\n" + "Força:" + this.strength + "\n" + "Dextreza:" + this.dexterity, 1);
	}

	public boolean isAlive() {
		return (this.life > 0.0);
	}

	public void rest() {
		if (this.life < this.maxlife) {
			this.life++;
			Tool.dialog("Restaurando vida",
					"Você restaurou 1 ponto de vida, agora você possui " + this.life + " pontos de vida!", 0);
		} else {
			Tool.dialog("Negado", "Você já está totalmente restaurado!", 0);
		}
	}

	public void attack(Monster target) {
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
					Tool.dialog("Dano Máximo!", "Você acertou um golpe crítico!!!", 2);
					target.takeDamage(getAttack());
				} else {
					Tool.dialog("Causou um golpe!.", "Você acertou o inimigo. O inimigo perdeu " + this.attack
							+ " pontos de vida." + " Agora ele possui " + damage + " pontos de vida", 2);
					target.takeDamage(getAttack());
				}
			} else {
				Tool.dialog("Errou", "Você não acertou o inimigo...", 2);
			}
		} else {
			Tool.dialog("Vitória", "O inimigo perdeu " + this.attack + " pontos de vida. Você derrotou o inimigo!", 2);
			target.takeDamage(getAttack());
		}
	}

	public void takeDamage(float targetAttack) {
		this.life = this.life - targetAttack;
		if (this.life < 0.0) {
			this.life = 0;
		}
	}

	public String getAction() {
		setOptions();
		return action = options[Tool.inputDialogOptions("Momento de decidir...", "O que você deseja fazer?", options)];
	}

	public String getName() {
		return this.name;
	}

	public Integer getLevel() {
		return this.level;
	}

	public float getLife() {
		return this.life;
	}

	public float getAttack() {
		return this.attack;
	}

	public void setOptions() {
		for (int i = 0; i < options.length; i++) {
			options[i] = "***";
		}
		if (Battle.isBattleOn()) {
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Atacar";
					break;
				}
			}
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Hero Status";
					break;
				}
			}
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Enemy Status";
					break;
				}
			}
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Fugir";
					break;
				}
			}
		}
		if (!Battle.isBattleOn()) {
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Descansar";
					break;
				}

			}
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Hero Status";
					break;
				}
			}
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Upgrade";
					break;
				}

			}
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals("***")) {
					options[i] = "Explorar Dungeon";
					break;
				}
			}
		}
	}
}
