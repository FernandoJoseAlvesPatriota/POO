package blackjack.players;

import blackjack.core.*;


public class SmartPlayer extends BettingPlayer {
	
	public SmartPlayer(String name, Hand hand, Bank bank) {
		super(name, hand, bank);
	}
	
	public boolean hit (Dealer dealer) {
		if(getHand().total() > 11 ) {
			return false;
		}
		return true;
	}
	
	public void bet() {
		Bank getBank = null;
		getBank.place10Bet();
	}
	
	public boolean doubleDown(Dealer dealer) {
		return false;
	}
}
