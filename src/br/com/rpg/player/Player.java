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
	protected Integer max_life = 0;
	protected Integer strength = 0;
	protected Integer max_strength = 0;
	protected Integer dexterity = 0;
	protected Integer max_dexterity = 0;
	protected Integer health_potion = 0;
	protected Integer max_health_potion = 0;
	protected static String action = "";
	protected float attack;
	protected Integer souls = 0;
	protected String[] options = { "" };
	static ArrayList<String> options_list = new ArrayList<String>();
	DecimalFormat formated_float = new DecimalFormat("#.##");

	public void createPlayer(Integer level) {
		setName();
		this.level = level;
		this.max_life = level + 9;
		this.life = this.max_life;
		this.max_strength = level;
		this.strength = max_strength;
		this.max_dexterity = level;
		this.dexterity = this.max_dexterity;
	}

	public void upgradeStatus() {

		int x = 0;
		String[] options = { "Vida", "For�a", "Destreza" };
		String stat = options[Tool.inputDialogOptions("Upgrade de atributo",
				"Voc� possui " + this.souls + " souls.\nQual atributo voc� quer melhorar?", options)];
		while (x == 0) {

			if (stat.equalsIgnoreCase("Vida")) {
				if (this.souls >= (this.level * 2)) {
					this.max_life = this.max_life + 5;
					this.life = this.max_life;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Voc� aprimorou sua vida em 5 pontos.", 2);
					break;
				} else {
					Tool.dialog("Negado",
							"Voc� n�o tem souls o suficiente.\nVoc� precisa de " + this.level * 2 + " souls para isto.",
							0);
					break;
				}
			} else if (stat.equalsIgnoreCase("For�a")) {
				if (this.souls >= (this.level * 2)) {
					this.max_strength++;
					this.strength = this.max_strength;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Voc� aprimorou sua for�a em 1 ponto.", 2);
					break;
				} else {
					Tool.dialog("Negado",
							"Voc� n�o tem souls o suficiente\nVoc� precisa de " + this.level * 2 + " souls para isto.",
							0);
					break;
				}
			} else if (stat.equalsIgnoreCase("Destreza")) {
				if (this.souls >= (this.level * 2)) {
					this.max_dexterity++;
					this.dexterity = this.max_dexterity;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Voc� aprimorou sua Destreza em 1 ponto  .", 2);
					break;
				} else {
					Tool.dialog("Negado",
							"Voc� n�o tem souls o suficiente\nVoc� precisa de " + this.level * 2 + " souls para isto.",
							0);
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
				"Nome: " + this.name + "\nLevel: " + this.level + "\nVida: " + formated_float.format(this.life)
						+ "\nFor�a: " + this.strength + "\nDestreza: " + this.dexterity + "\nPo��es de vida: "
						+ this.health_potion + "\nSouls: " + this.souls,
				1);
	}

	public boolean isAlive() {
		return (this.life > 0);
	}

	public void rest() {
		if (this.life < this.max_life) {
			this.life = this.max_life;
			Tool.dialog("Restaurando vida",
					"Voc� restaurou sua de vida, agora voc� possui " + this.life + " pontos de vida!", 0);
		} else {
			Tool.dialog("Negado", "Voc� j� est� totalmente restaurado!", 0);
		}
	}

	public void useHealthPotion() {
		if (this.health_potion >= 1) {
			if (this.life + (this.max_life / 4) <= this.max_life) {
				this.life = this.life + (this.max_life / 4);
				this.health_potion--;
				Tool.dialog("", "Voc� bebeu uma po��o de vida e restaurou " + this.max_life / 4 + " pontos de vida.\n"
						+ "Agora voc� possui " + this.health_potion + " po��es de vida", 2);
			}
		} else {
			Tool.dialog("Negado", "Voc� n�o possui po��es de vida!", 2);
		}
	}

	public void buyHealthPotion() {
		this.max_health_potion = this.level;
		if (this.health_potion < this.max_health_potion) {
			if (this.souls >= this.level * 2) {
				this.health_potion++;
				Tool.dialog("Item no invent�rio...",
						"Voc� comprou uma po��o de vida.\nAgora voc� possui " + this.health_potion + " po��es de vida.",
						2);
				this.souls = this.souls - this.level * 2;
			}
		}else{
			Tool.dialog("Negado",
					"Voc� n�o tem souls o suficiente\nVoc� precisa de " + this.level * 2 + " souls para isto.",
					0);
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
									+ " Agora ele possui " + formated_float.format(target.getLife() - this.attack)
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
		options_list.clear();
		if (Battle.isBattleOn()) {
			options_list.add("Atacar");
			options_list.add("Po��o de vida");
			options_list.add("Hero Status");
			options_list.add("Enemy Status");
			options_list.add("Fugir");
		}
		if (!Battle.isBattleOn()) {
			options_list.add("Descansar");
			options_list.add("Hero Status");
			options_list.add("Upgrade");
			options_list.add("Comprar Po��o de Vida");
			options_list.add("Explorar Dungeon");

		}
		this.options = options_list.toArray(new String[options_list.size()]);
		return options;
	}
}
