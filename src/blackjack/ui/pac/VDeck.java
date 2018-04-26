package blackjack.ui.pac;

import blackjack.core.*;
import java.util.Iterator;

public class VDeck extends Deck {
	
	protected void buildCards() {
		
		//Isso é horrivel, mais é melhor do que os laços e as estruturas condicionais alternativas
		Card[] deck = new Card [52];
		setDeck (deck);
		
		
		deck[0] = new VCard (Suit.HEARTS, Rank.TWO, "/blackjack/ui/bitmaps/h2");
		deck[1] = new VCard (Suit.HEARTS, Rank.THREE, "/blackjack/ui/bitmaps/h3");
		deck[2] = new VCard (Suit.HEARTS, Rank.FOUR, "/blackjack/ui/bitmaps/h4");
		deck[3] = new VCard (Suit.HEARTS, Rank.FIVE, "/blackjack/ui/bitmaps/h5");
		deck[4] = new VCard (Suit.HEARTS, Rank.SIX, "/blackjack/ui/bitmaps/h6");
		deck[5] = new VCard (Suit.HEARTS, Rank.SEVEN, "/blackjack/ui/bitmaps/h7");
		deck[6] = new VCard (Suit.HEARTS, Rank.EIGHT, "/blackjack/ui/bitmaps/h8");
		deck[7] = new VCard (Suit.HEARTS, Rank.NINE, "/blackjack/ui/bitmaps/h9");
		deck[8] = new VCard (Suit.HEARTS, Rank.TEN, "/blackjack/ui/bitmaps/h10");
		deck[9] = new VCard (Suit.HEARTS, Rank.JACK, "/blackjack/ui/bitmaps/h11");
		deck[10] = new VCard (Suit.HEARTS, Rank.QUEEN, "/blackjack/ui/bitmaps/h12");
		deck[11] = new VCard (Suit.HEARTS, Rank.KING, "/blackjack/ui/bitmaps/h13");
		deck[12] = new VCard (Suit.HEARTS, Rank.ACE, "/blackjack/ui/bitmaps/h1");
		deck[13] = new VCard (Suit.DIAMONDS, Rank.TWO, "/blackjack/ui/bitmaps/d2");
		deck[14] = new VCard (Suit.DIAMONDS, Rank.THREE, "/blackjack/ui/bitmaps/d3");
		deck[15] = new VCard (Suit.DIAMONDS, Rank.FOUR, "/blackjack/ui/bitmaps/d4");
		deck[16] = new VCard (Suit.DIAMONDS, Rank.FIVE, "/blackjack/ui/bitmaps/d5");
		deck[17] = new VCard (Suit.DIAMONDS, Rank.SIX, "/blackjack/ui/bitmaps/d6");
		deck[18] = new VCard (Suit.DIAMONDS, Rank.SEVEN, "/blackjack/ui/bitmaps/d7");
		deck[19] = new VCard (Suit.DIAMONDS, Rank.EIGHT, "/blackjack/ui/bitmaps/d8");
		deck[20] = new VCard (Suit.DIAMONDS, Rank.NINE, "/blackjack/ui/bitmaps/d9");
		deck[21] = new VCard (Suit.DIAMONDS, Rank.TEN, "/blackjack/ui/bitmaps/d10");
		deck[22] = new VCard (Suit.DIAMONDS, Rank.JACK, "/blackjack/ui/bitmaps/d11");
		deck[23] = new VCard (Suit.DIAMONDS, Rank.QUEEN, "/blackjack/ui/bitmaps/d12");
		deck[24] = new VCard (Suit.DIAMONDS, Rank.KING, "/blackjack/ui/bitmaps/d13");
		deck[25] = new VCard (Suit.DIAMONDS, Rank.ACE, "/blackjack/ui/bitmaps/d1");
		deck[26] = new VCard (Suit.SPADES, Rank.TWO, "/blackjack/ui/bitmaps/s2");
		deck[27] = new VCard (Suit.SPADES, Rank.THREE, "/blackjack/ui/bitmaps/s3");
		deck[28] = new VCard (Suit.SPADES, Rank.FOUR, "/blackjack/ui/bitmaps/s4");
		deck[29] = new VCard (Suit.SPADES, Rank.FIVE, "/blackjack/ui/bitmaps/s5");
		deck[30] = new VCard (Suit.SPADES, Rank.SIX, "/blackjack/ui/bitmaps/s6");
		deck[31] = new VCard (Suit.SPADES, Rank.SEVEN, "/blackjack/ui/bitmaps/s7");
		deck[32] = new VCard (Suit.SPADES, Rank.EIGHT, "/blackjack/ui/bitmaps/s8");
		deck[33] = new VCard (Suit.SPADES, Rank.NINE, "/blackjack/ui/bitmaps/s9");
		deck[34] = new VCard (Suit.SPADES, Rank.TEN, "/blackjack/ui/bitmaps/s10");
		deck[35] = new VCard (Suit.SPADES, Rank.JACK, "/blackjack/ui/bitmaps/s11");
		deck[36] = new VCard (Suit.SPADES, Rank.SIX, "/blackjack/ui/bitmaps/s12");
		deck[37] = new VCard (Suit.SPADES, Rank.KING, "/blackjack/ui/bitmaps/s13");
		deck[38] = new VCard (Suit.SPADES, Rank.ACE, "/blackjack/ui/bitmaps/s1");
		deck[39] = new VCard (Suit.CLUBS, Rank.TWO, "/blackjack/ui/bitmaps/c2");
		deck[40] = new VCard (Suit.CLUBS, Rank.THREE, "/blackjack/ui/bitmaps/c3");
		deck[41] = new VCard (Suit.CLUBS, Rank.FOUR, "/blackjack/ui/bitmaps/c4");
		deck[42] = new VCard (Suit.CLUBS, Rank.FIVE, "/blackjack/ui/bitmaps/c5");
		deck[43] = new VCard (Suit.CLUBS, Rank.SIX, "/blackjack/ui/bitmaps/c6");
		deck[44] = new VCard (Suit.CLUBS, Rank.SEVEN, "/blackjack/ui/bitmaps/c7");
		deck[45] = new VCard (Suit.CLUBS, Rank.EIGHT, "/blackjack/ui/bitmaps/c8");
		deck[46] = new VCard (Suit.CLUBS, Rank.NINE, "/blackjack/ui/bitmaps/c9");
		deck[47] = new VCard (Suit.CLUBS, Rank.TEN, "/blackjack/ui/bitmaps/c10");
		deck[48] = new VCard (Suit.CLUBS, Rank.JACK, "/blackjack/ui/bitmaps/c11");
		deck[49] = new VCard (Suit.CLUBS, Rank.QUEEN, "/blackjack/ui/bitmaps/c12");
		deck[50] = new VCard (Suit.CLUBS, Rank.KING, "/blackjack/ui/bitmaps/c13");
		deck[51] = new VCard (Suit.CLUBS, Rank.ACE, "/blackjack/ui/bitmaps/c1");
		
	}

}
