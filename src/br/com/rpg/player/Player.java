package br.com.rpg.player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import br.com.rpg.battle.Battle;
import br.com.rpg.monster.Monster;
import br.com.rpg.tool.*;

public class Player {

	protected Scanner read = new Scanner(System.in);
	protected String name = "";
	protected Integer level = 0;
	protected float life = 0;
	protected Integer maxlife = 0;
	protected Integer strength = 0;
	protected Integer maxstrength = 0;
	protected Integer dexterity = 0;
	protected Integer maxdexterity = 0;
	protected static String action = "";
	protected float attack;
	protected Integer souls = 0;
	protected String[] options = { "" };
	static ArrayList<String> op��es = new ArrayList<String>();
	DecimalFormat df = new DecimalFormat("#.##");

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
		String[] options = { "Life", "Strength", "Dexterity" };
		String stat = options[Tool.inputDialogOptions("Upgrade de atributo", "Qual atributo voc� quer melhorar?",
				options)];
		while (x == 0) {

			if (stat.equalsIgnoreCase("life")) {
				if (this.souls >= (this.level * 2)) {
					this.maxlife = this.maxlife + 5;
					this.life = this.maxlife;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Voc� aprimorou sua vida em 5 pontos.", 2);
					break;
				} else {
					Tool.dialog("Negado", "Voc� n�o tem souls o suficiente para isto", 0);
					break;
				}
			} else if (stat.equalsIgnoreCase("strength")) {
				if (this.souls >= (this.level * 2)) {
					this.maxstrength++;
					this.strength = this.maxstrength;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Voc� aprimorou sua for�a em 1 ponto.", 2);
					break;
				} else {
					Tool.dialog("Negado", "Voc� n�o tem souls o suficiente para isto", 0);
					break;
				}
			} else if (stat.equalsIgnoreCase("dexterity")) {
				if (this.souls >= (this.level * 2)) {
					this.maxdexterity++;
					this.dexterity = this.maxdexterity;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Voc� aprimorou sua dextreza em 1 ponto  .", 2);
					break;
				} else {
					Tool.dialog("Negado", "Voc� n�o tem souls o suficiente para isto", 0);
					break;
				}
			} else {
				Tool.dialog("Atributo Inv�lido", "Voc� n�o digitou corretamente...", 3);
				stat = Tool.inputDialog("Upgrade de atributo", "Qual atributo, voc� deseja aprimorar?", 3);
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
		Tool.dialog("Status do Personagem",
				"Nome: " + this.name + "\n" + "Level: " + this.level + "\n" + "Vida: " + df.format(this.life) + "\n"
						+ "For�a: " + this.strength + "\n" + "Dextreza: " + this.dexterity + "\n" + "Souls: "
						+ this.souls,
				1);
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void rest() {
		if (this.life < this.maxlife) {
			this.life = this.maxlife;
			Tool.dialog("Restaurando vida",
					"Voc� restaurou sua de vida, agora voc� possui " + this.life + " pontos de vida!", 0);
		} else {
			Tool.dialog("Negado", "Voc� j� est� totalmente restaurado!", 0);
		}
	}

	public void attack(Monster target) {
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
					Tool.dialog("Dano M�ximo!", "Voc� acertou um golpe cr�tico!!!", 2);
					target.takeDamage(getAttack());
				} else {
					Tool.dialog("Causou um golpe!.",
							"Voc� acertou o inimigo. O inimigo perdeu " + this.attack + " pontos de vida."
									+ " Agora ele possui " + df.format(target.getLife() - this.attack)
									+ " pontos de vida",
							2);
					target.takeDamage(getAttack());
				}
			} else {
				Tool.dialog("Errou", "Voc� n�o acertou o inimigo...", 2);
			}
		} else {
			Tool.dialog("Vit�ria", "O inimigo perdeu " + this.attack + " pontos de vida. Voc� derrotou o inimigo!", 2);
			target.takeDamage(getAttack());
		}
	}

	public void takeDamage(float targetAttack) {
		this.life = this.life - targetAttack;
		if (this.life < 0) {
			this.life = 0;
		}
	}

	public String getAction() {
		setOptions();
		return action = options[Tool.inputDialogOptions("Momento de decidir...", "O que voc� deseja fazer?",
				setOptions())];
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

	public void setSouls(Monster target) {
		this.souls = this.souls + target.getSouls();
	}

	public String[] setOptions() {
		op��es.clear();

		if (Battle.isBattleOn()) {
			op��es.add("Atacar");
			op��es.add("Hero Status");
			op��es.add("Enemy Status");
			op��es.add("Fugir");
		}
		if (!Battle.isBattleOn()) {
			op��es.add("Descansar");
			op��es.add("Hero Status");
			op��es.add("Upgrade");
			op��es.add("Explorar Dungeon");

		}
		this.options = op��es.toArray(new String[op��es.size()]);
		return options;
	}
}
