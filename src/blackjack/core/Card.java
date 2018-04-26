package blackjack.core;

public class Card {
	
	private static Rank rank;
	private Suit suit;
	private boolean face_up;
	
	public Card (Suit suit, Rank rank) {
		
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	public static Rank getRank() {
		return rank;
	}
	public void setFaceUp (boolean up) {
		face_up = up;
	}
	
	public boolean isFaceUp() {
		return face_up;
	}
	
	public String toString() {
		if(!isFaceUp()) {
			return "Hidden";
		}
		return rank.toString() + suit.toString();
	}
}
