package blackjack.ui.mvc;

import blackjack.core.*;

public class VCard extends Card {
	
	private String image;
	
	public VCard(Suit suit, Rank rank, String image) {
		super(suit, rank);
		this.image = image;
	}
	
	public String getImage() {
		if(isFaceUp()) {
			return image;
		} else {
			return "/blackjack/ui/bitmaps/empty_pile.xbm";
		}
		
	}

}
