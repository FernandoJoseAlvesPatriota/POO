package blackjack.players;

import blackjack.core.*;

public class KnowledgeablePlayer extends BettingPlayer {
	
	public KnowledgeablePlayer (String name, Hand hand, Bank bank) {
		super(name, hand, bank);
	}
	public boolean doubleDown(Dealer dealer) {
		int total = getHand().total();
		if(total == 10 || total == 11) {
			return true;
		}
		return false;
	}
	
	public boolean hit (Dealer dealer) {
		
		int total = getHand().total();
		Card card = dealer.getUpCard();
		
		//nunca distribui mais cartas, independente de qual, se total > 15
		if(total > 15) {
			return false;
		}
		//sempre distribui mais cartas para 11 e menos
		if(total<= 11) {
			return true;
		}
		
		//isso deixa 11,12,13,14
		//baseia a decisão na banca
		
		if(card.getRank().getRank() > 7) {
			return true;
		}
		return false;
				}
	
	public void bet() {
		getBank().place10Bet();
		
	}
	
}
