package blackjack.players;

import blackjack.core.*;

public class SafePlayer extends BettingPlayer {
	
	public SafePlayer(String name, Hand hand, Bank bank) {
		super(name, hand, bank);
	}
	public boolean hit (Dealer dealer) {
		return false;
	}
	public boolean doubleDown(Dealer dealer) {
		return false;
	}
	
	public void bet() {
		getBank().place10Bet();
	}

}
