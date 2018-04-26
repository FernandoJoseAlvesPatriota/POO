package blackjack.ui;

import blackjack.core.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Console implements PlayerListener {
	
	//console singleton
	public final static Console INSTANCE = new Console();
	
	private BufferedReader in = 
			new BufferedReader (new InputStreamReader(System.in));
	
	public void printMessage (String message) {
		System.out.println(message);
	}
	
	public String readInput (String default_input) {
		
		String response;
		try {
			return in.readLine();
		} catch (IOException ioe) {
			return default_input;
		}
	}
	
	public void playerChanged (Player player) {
		printMessage(player.toString());
	}
	public void playerBusted (Player player) {
		printMessage(player.toString() + "BUSTED!");
	}
	public void playerBlackjack (Player player) {
		printMessage(player.toString() + "BLACKJACK!");
	}
	public void playerStanding (Player player) {
		printMessage(player.toString() + "STANDING");
	}
	public void playerWon (Player player) {
		printMessage(player.toString() + "WINNER!");
	}
	public void playerLost (Player player) {
		printMessage(player.toString() + "LOSER!");
	}
	public void playerStandoff (Player player) {
		printMessage(player.toString() + "STANDOFF");
	}
	//privado para evitar instanciação
	private Console() {}
}