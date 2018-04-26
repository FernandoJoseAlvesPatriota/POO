package blackjack.players;

import blackjack.core.Bank;
import blackjack.core.BettingPlayer;
import blackjack.core.Dealer;
import blackjack.core.Hand;
import blackjack.players.*;

public class OneHitPlayer extends BettingPlayer {
	
	private boolean has_hit = false;
	
	public OneHitPlayer(String name, Hand hand, Bank bank) {
		super(name, hand, bank);
	}
	
	public boolean hit (Dealer dealer) {
		if(!has_hit){
			has_hit = true;
			return true;
		}
		return false;
	}
	public void reset() {
		super.reset();
		has_hit = false;
	}
	public void bet() {
		getBank().place10Bet();
	}
	public boolean doubleDown(Dealer dealer) {
		return false;
	}
}
