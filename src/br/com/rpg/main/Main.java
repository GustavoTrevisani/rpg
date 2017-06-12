package br.com.rpg.main;


import br.com.rpg.monster.Monster;
import br.com.rpg.player.Player;
import br.com.rpg.tool.*;

public class Main {

	static Player player = new Player(); 
	static Monster monster = new Monster();
	static Tool delay = new Tool(); //Ferramenta para delay
	static Tool rn = new Tool(); //Gerador de números aleatórios
	static Tool printer = new Tool(); //Ferramentar para printar na tela
	
	public static void main(String[] args) {

		player.createPlayer(1); //criando status do player com o level "1"
		
		
		
		System.out.println(player.attack());
	}
}