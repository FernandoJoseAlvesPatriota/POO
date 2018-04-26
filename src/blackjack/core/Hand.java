package blackjack.core;

import java.util.ArrayList;
import java.util.Iterator;

public class Hand {
	
	private ArrayList cards = new ArrayList();
	private static final int BLACKJACK = 21;
	private HandListener holder;
	private int number_aces;
	
	public Hand () {
		setHolder(
			new HandListener() {
				
				public void handPlayable() {}
				public void handBlackjack() {}
				public void handBusted() {}
				public void handChanged() {}
				
			}
				);
	}
	
	public void setHolder (HandListener holder) {
		this.holder = holder;
	}
	
	public Iterator getCards() {
		return cards.iterator();
	}
	public void addCard(Card card) {
		cards.add(card);
		
		holder.handChanged();
		
		if(card.getRank() == Rank.ACE) {
			number_aces++;
		}
		if(bust()) {
			holder.handBusted();
			return;
		}
		if(blackjack()) {
			return;
		}
	}

	
	public boolean canDoubleDown() {
		return (cards.size() == 2);
	}
	
	public boolean isEqual (Hand hand) {
		if(hand.total() == this.total()) {
			return true;			
		}
		return false;
		}
	
	public boolean isGreaterThan(Hand hand) {
		return this.total() > hand.total();
	}
	
	public boolean blackjack() {
		if(cards.size() == 2  && total() == BLACKJACK) {
			return true;
		}
		return false;
	}
	
	public void reset() {
		Iterator i = cards.iterator();
		while (i.hasNext()) {
			Card card = (Card) i.next();
			card.setFaceUp(true);
		}
	}
	
		public String toString () {
			Iterator i=cards.iterator();
			String string = "";
			while (i.hasNext()) {
				Card card = (Card) i.next();
				string = string + "" + card.toString();
			}
			return string;
		}
		
		public int total() {
			int total = 0;
			Iterator i = cards.iterator();
			while (i.hasNext()) {
				Card card = (Card) i.next();
				total += card.getRank().getRank();
			}
			
			int temp_aces = number_aces;
			while (total> BLACKJACK && temp_aces > 0) {
				total = total - 10;
				temp_aces--;
			}
			return total;
		}
		
		private boolean bust () {
			if(total() > BLACKJACK) {
				return true;
			}
			return false;
		}

		public void turnOver() {
			// TODO Auto-generated method stub
			
		}
}
