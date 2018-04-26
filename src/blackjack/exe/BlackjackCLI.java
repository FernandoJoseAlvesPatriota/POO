package blackjack.exe;

import blackjack.core.*;
import blackjack.players.*;
import blackjack.ui.*;

public class BlackjackCLI {

	public static void main (String [] args) {
		Deckpile cards = new Deckpile();
		for (int i=0; i<4 ; i++) {
			cards.shuffle();
			Deck deck = new Deck();
			deck.addToStack(cards);
			cards.shuffle();			
		}
		
		Hand dealer_hand = new Hand();
		BlackjackDealer dealer = new BlackjackDealer("Dealer", dealer_hand, cards); Bank human_bank = new Bank(1000);
		Hand human_hand = new Hand();
		Player player = new CommandLinePlayer("Human",human_hand, human_bank);
		dealer.addListener( Console.INSTANCE);
		player.addListener( Console.INSTANCE);
		dealer.addPlayer(player);
		
		do {
			dealer.newGame();
		} while (playAgain());
		
		Console.INSTANCE.printMessage("Thank you for playing!");
	}
	
		private static boolean playAgain() {
			Console.INSTANCE.printMessage("Would you like to play again? [Y]es [N]o");
			String response = Console.INSTANCE.readInput ("invalid");
			if(response.equalsIgnoreCase("y")) {
				return true;
			}
				return false;
		}
}
