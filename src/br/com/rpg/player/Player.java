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
		String[] options = { "Vida", "Força", "Destreza" };
		String stat = options[Tool.inputDialogOptions("Upgrade de atributo",
				"Você possui " + this.souls + " souls.\nQual atributo você quer melhorar?", options)];
		while (x == 0) {

			if (stat.equalsIgnoreCase("Vida")) {
				if (this.souls >= (this.level * 2)) {
					this.max_life = this.max_life + 5;
					this.life = this.max_life;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Você aprimorou sua vida em 5 pontos.", 2);
					break;
				} else {
					Tool.dialog("Negado",
							"Você não tem souls o suficiente.\nVocê precisa de " + this.level * 2 + " souls para isto.",
							0);
					break;
				}
			} else if (stat.equalsIgnoreCase("Força")) {
				if (this.souls >= (this.level * 2)) {
					this.max_strength++;
					this.strength = this.max_strength;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Você aprimorou sua força em 1 ponto.", 2);
					break;
				} else {
					Tool.dialog("Negado",
							"Você não tem souls o suficiente\nVocê precisa de " + this.level * 2 + " souls para isto.",
							0);
					break;
				}
			} else if (stat.equalsIgnoreCase("Destreza")) {
				if (this.souls >= (this.level * 2)) {
					this.max_dexterity++;
					this.dexterity = this.max_dexterity;
					this.souls = this.souls - (this.level * 2);
					this.level++;
					Tool.dialog("Atributo melhorado", "Você aprimorou sua Destreza em 1 ponto  .", 2);
					break;
				} else {
					Tool.dialog("Negado",
							"Você não tem souls o suficiente\nVocê precisa de " + this.level * 2 + " souls para isto.",
							0);
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
		Tool.dialog("Status do Personagem",
				"Nome: " + this.name + "\nLevel: " + this.level + "\nVida: " + formated_float.format(this.life)
						+ "\nForça: " + this.strength + "\nDestreza: " + this.dexterity + "\nPoções de vida: "
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
					"Você restaurou sua de vida, agora você possui " + this.life + " pontos de vida!", 0);
		} else {
			Tool.dialog("Negado", "Você já está totalmente restaurado!", 0);
		}
	}

	public void useHealthPotion() {
		if (this.health_potion >= 1) {
			if (this.life + (this.max_life / 4) <= this.max_life) {
				this.life = this.life + (this.max_life / 4);
				this.health_potion--;
				Tool.dialog("", "Você bebeu uma poção de vida e restaurou " + this.max_life / 4 + " pontos de vida.\n"
						+ "Agora você possui " + this.health_potion + " poções de vida", 2);
			}
		} else {
			Tool.dialog("Negado", "Você não possui poções de vida!", 2);
		}
	}

	public void buyHealthPotion() {
		this.max_health_potion = this.level;
		if (this.health_potion < this.max_health_potion) {
			if (this.souls >= this.level * 2) {
				this.health_potion++;
				Tool.dialog("Item no inventário...",
						"Você comprou uma poção de vida.\nAgora você possui " + this.health_potion + " poções de vida.",
						2);
				this.souls = this.souls - this.level * 2;
			}
		}else{
			Tool.dialog("Negado",
					"Você não tem souls o suficiente\nVocê precisa de " + this.level * 2 + " souls para isto.",
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
					Tool.dialog("Dano Máximo!", "Você acertou um golpe crítico!!!", 2);
					target.takeDamage(getAttack());
				} else {
					Tool.dialog("Causou um golpe!.",
							"Você acertou o inimigo. O inimigo perdeu " + this.attack + " pontos de vida."
									+ " Agora ele possui " + formated_float.format(target.getLife() - this.attack)
									+ " pontos de vida",
							2);
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
		if (this.life < 0) {
			this.life = 0;
		}
	}

	public String getAction() {
		setOptions();
		return action = options[Tool.inputDialogOptions("Momento de decidir...", "O que você deseja fazer?",
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
			options_list.add("Poção de vida");
			options_list.add("Hero Status");
			options_list.add("Enemy Status");
			options_list.add("Fugir");
		}
		if (!Battle.isBattleOn()) {
			options_list.add("Descansar");
			options_list.add("Hero Status");
			options_list.add("Upgrade");
			options_list.add("Comprar Poção de Vida");
			options_list.add("Explorar Dungeon");

		}
		this.options = options_list.toArray(new String[options_list.size()]);
		return options;
	}
}
