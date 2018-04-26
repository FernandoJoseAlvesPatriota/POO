package blackjack.core;

import java.util.Iterator;
import java.util.Random;


public class Deck {

	private Card []  deck;
	private int index;
	
public Deck() {
	buildCards();
}

	public void addToStack( Deckpile stack) {
		stack.addCards(deck);
	}
	protected void setDeck( Card[] deck) {
		this.deck = deck;
	}
	
	protected void buildCards() {
		
		deck = new Card[52];
		Iterator suits = Suit.SUITS.iterator();
		
		int counter = 0;
		while (suits.hasNext()) {
			Suit suit = (Suit) suits.next();
			Iterator ranks = Rank.RANKS.iterator();
			while (ranks.hasNext()) {
				Rank rank = (Rank) ranks.next();
				deck[counter] = new Card(suit, rank);
				counter++;
			}
		}
	}
	
}
