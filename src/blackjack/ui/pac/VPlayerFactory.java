package blackjack.ui.pac;

import blackjack.core.*;


public class VPlayerFactory {
	
	private VBlackjackDealer dealer;
	private GUIPlayer human;
	private Deckpile pile;
	
	
	public VBlackjackDealer getDealer() {
		
		//cria e retorna apenas um
		if(dealer == null) {
			VHand dealer_hand = getHand();
			Deckpile cards = getCards();
			dealer = new VBlackjackDealer("Dealer", dealer_hand, cards);
		}
		return dealer;
	}
	
	public GUIPlayer getHuman() {
		//cria e retorna apenas um
		if(human == null) {
			VHand human_hand = getHand();
			Bank bank = new Bank(1000);
			human = new GUIPlayer ("Human", human_hand, bank, getDealer() );
		}
		return human;
	}
	
	public Deckpile getCards() {
		//cria e retorna apenas um
		if(pile == null) {
			pile = new Deckpile();
			for(int i = 0; i < 4; i ++) {
				pile.shuffle();
				Deck deck = new VDeck();
				deck.addToStack(pile);
				pile.shuffle();
			}
		}
		return pile;
	}
	private VHand getHand() {
		return new VHand();
	}

}
