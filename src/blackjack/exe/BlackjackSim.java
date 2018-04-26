package blackjack.exe;

import blackjack.core.*;
import blackjack.ui.*;
import blackjack.players.*;
import blackjack.ui.Console;

public class BlackjackSim {
	
	public static void main (String[] args) {
		
		Console.INSTANCE.printMessage("How many times should the simulator plays?");
		String response = Console.INSTANCE.readInput ("invalid");
		int loops = Integer.parseInt(response);
		
		Deckpile cards = new Deckpile();
		for (int i=0; i <4; i++) {
			cards.shuffle();
			Deck deck = new Deck();
			deck.addToStack(cards);
			cards.shuffle();
		}
		
		//cria uma banca
		Hand dealer_hand = new Hand();
		BlackjackDealer dealer = new BlackjackDealer ("Dealer", dealer_hand, cards);
		
		//cria um OneHitPlayer
		Bank one_bank = new Bank (1000);
		Hand one_hand = new Hand();
		Player oplayer = new OneHitPlayer ("OneHit", one_hand, one_bank);
		
		//cria um SmartPlayer
		Bank smart_bank = new Bank (1000);
		Hand smart_hand = new Hand();
		Player smplayer = new SmartPlayer ("Smart", smart_hand, smart_bank);
		
		//cria uma SafePlayer,
		Bank safe_bank = new Bank (1000);
		Hand safe_hand = new Hand();
		Player splayer = new SafePlayer ("Safe", safe_hand, safe_bank);
		
		//cria um FlipPlayer
		Bank flip_bank = new Bank (1000);
		Hand flip_hand = new Hand();
		Player fplayer = new FlipPlayer ("Flip", flip_hand, flip_bank);
		
		//cria um jogar inteligente
		Bank kn_bank = new Bank(1000);
		Hand kn_hand = new Hand();
		Player knplayer = new KnowledgeablePlayer ("Knowledgeable", kn_hand,kn_bank);
		
		//cria um jogador "ótimo"
		Bank opt_bank = new Bank (1000);
		Hand opt_hand = new Hand();
		Player optplayer = new OptimalPlayer ("Optimal", opt_hand, opt_bank);
		
		//reune todos os jogadores
		dealer.addListener(Console.INSTANCE);
		oplayer.addListener(Console.INSTANCE);
		dealer.addPlayer(oplayer);
		splayer.addListener(Console.INSTANCE);
		dealer.addPlayer(smplayer);
		fplayer.addListener(Console.INSTANCE);
		dealer.addPlayer(fplayer);
		knplayer.addListener(Console.INSTANCE);
		dealer.addPlayer(knplayer);
		optplayer.addListener(Console.INSTANCE);
		dealer.addPlayer(optplayer);
		
		int counter = 0;
		while (counter < loops) {
			dealer.newGame();
			counter++;
		}
		
	}

}

//PAROU NA PÁGINA 652