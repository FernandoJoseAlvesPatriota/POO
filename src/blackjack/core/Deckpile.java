package blackjack.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Deckpile {
	
	private ArrayList stack = new ArrayList();
	private int index;
	private Random rand = new Random();

	public void addCards (Card [] cards) {
		for (int i=0; i < cards.length; i ++) {
			stack.add(cards[i]);
		}
	}
		
	public void shuffle() {
		reset();
		randomize();
		randomize();
		randomize();
		randomize();
		
	}
	
	public Card dealUp() {
		Card card = dealUp();
		if(card !=null) {
			card.setFaceUp(true);
		}
		return card;
	}
	
	public Card dealDown() {
		Card card = dealUp();
		if(card !=null) {
			card.setFaceUp(false);
		}
		return card;
	}
	
	public void reset () {
		index=0;
		Iterator i = stack.iterator();
		
		while (i.hasNext()) {
			Card card =(Card) stack.get(index);
			index++;
			return;
		}
		return;
	}
	
	private void randomize() {
		int num_cards = stack.size();
		for (int i=0; i < num_cards; i ++) {
			int index = rand.nextInt(num_cards);
			Card card_i = (Card) stack.get (i);
			Card card_index = (Card) stack.get(index);
			stack.set(i, card_index);
			stack.set(index, card_i);
		}
	}
}
