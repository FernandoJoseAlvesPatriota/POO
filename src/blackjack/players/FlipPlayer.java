package blackjack.players;

import blackjack.core.*;

public class FlipPlayer extends BettingPlayer {
	
	private boolean hit = false;
	private boolean should_hit_once = false;
	
	public FlipPlayer (String name, Hand hand, Bank bank) {
		super (name, hand, bank);
	}
	
	public boolean hit(Dealer dealer) {
		if(should_hit_once && !hit) {
			hit = true;
			return true;
		}
		return false;
	}
	
	public void reset() {
		super.reset();
		hit = false;
		should_hit_once = !should_hit_once;
	}
	public void bet() {
		getBank().place10Bet();
	}
	public boolean doubleDown(Dealer dealer) {
		return false;
	}
}
