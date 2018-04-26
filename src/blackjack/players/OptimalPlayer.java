package blackjack.players;

import blackjack.core.*;

public class OptimalPlayer extends BettingPlayer {
	
	public OptimalPlayer (String name, Hand hand,Bank bank) {
		super(name, hand, bank);
	}
	
	public boolean doubleDown(Dealer dealer) {
		int total = getHand().total();
		Card card = dealer.getUpCard();
		if (total == 11) {
			return true;
		}
		if (total == 10) {
		if(card.getRank().getRank() != Rank.TEN.getRank() && 
				Card.getRank() !=Rank.ACE) {
			return true;
		}		
		return false;
		}

	if(total == 9) {
		if(card.getRank() == Rank.TWO || 
				card.getRank() == Rank.THREE ||
				card.getRank() == Rank.FOUR ||
				card.getRank() == Rank.SIX ) {
			return true;
		}
		return false;
	}
	return false;
}

	public boolean hit(Dealer dealer) {
		
		int total = getHand().total();
		Card card = dealer.getUpCard();
		
		if(total >= 17) {
			return false;
		}
		
		if (total == 16) {
			if(card.getRank() == Rank.SEVEN ||
			card.getRank() == Rank.EIGHT || 
			card.getRank() == Rank.NINE ) {
				return true;
			} else {
				return false;
			}
		}
		if (total == 13 || total == 14 | total == 15) {
			if(card.getRank() == Rank.TWO ||
				card.getRank() == Rank.THREE ||
				card.getRank() == Rank.FOUR ||
				card.getRank() == Rank.FIVE ||
				card.getRank() == Rank.SIX ){
				return false;
			} else {
				return true;
			}
		}
		if(total == 12 ) {
			if (card.getRank() == Rank.FOUR ||
					card.getRank() == Rank.FIVE ||
					card.getRank() == Rank.SIX) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
		public void bet() {
			Bank getBank = null;
			getBank.place10Bet();
		}
}

