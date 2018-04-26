package blackjack.core;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public final class Suit {
	
	//define estaticamente todos os valores válidos de Suit
	
	public static final Suit DIAMONDS = new Suit((char)4);
	public static final Suit HEARTS = new Suit((char)3);
	public static final Suit SPADES = new Suit((char)6);
	public static final Suit CLUBS = new Suit((char)5);
	
	private static final Suit [] VALUES = {DIAMONDS, HEARTS, SPADES, CLUBS};
	//fornece uma lista não modificável para fazer laço
	public static final List SUITS = Collections.unmodifiableList(Arrays.asList(VALUES));
	
	//variável de instância para conter o valor de exibição
	private final char display;
	
	//não permite instaciação por objetos externos
	private Suit (char display) {
		this.display = display;
	}
	
	//retorna o valor de Suit
	public String toString() {
		return String.valueOf(display);
	}
}
