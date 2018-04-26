package blackjack.exe;

import blackjack.*;
import blackjack.core.Bank;
import blackjack.core.BlackjackDealer;
import blackjack.core.Deck;
import blackjack.core.Deckpile;
import blackjack.core.Hand;
import blackjack.core.Player;
import blackjack.core.threaded.*;
import blackjack.ui.mvc.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BlackjackKMVC extends JFrame {
	public static void main (String[] args) {
		JFrame frame = new BlackjackKMVC();
		frame.getContentPane().setBackground (FOREST_GREEN);
		frame.setSize(580,480);
		frame.show();
		
	}
	
	private BlackjackDealer dealer;
	private GUIPlayer human;
	private JPanel players = new JPanel (new GridLayout (0,1));
	
	private static final Color FOREST_GREEN = new Color (35,142,35);
	
	public BlackjackKMVC() {
		setUp();
			WindowAdapter wa = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			};
			addWindowListener(wa);
		}
		//precisa ser protegido se houver subclasse
		private PlayerView getPlayerView(Player player) {
			PlayerView view = new PlayerView(player);
			view.setBackground(FOREST_GREEN);
			return view;
		}
		
		//precisa ser protegido se houver subclasse
		private void setUp() {
			BlackjackDealer dealer = getDealer();
			PlayerView v1 = getPlayerView(dealer);
			
			GUIPlayer human = getHuman();
			PlayerView v2 = getPlayerView (human);
			
			PlayerView [] views = {v1,v2};
			addPlayers (views);
			
			dealer.addPlayer(human);
			
			addOptionView(human, dealer);
		}
		
		//precisa ser protegido se houver subclasse
		private void addPlayers (PlayerView [] pview) {
			players.setBackground(FOREST_GREEN);
			for (int i = 0; i < pview.length; i ++) {
				players.add(pview [i]);
			}
			
			getContentPane().add(players, BorderLayout.CENTER);
		}
		private void addOptionView (GUIPlayer human, BlackjackDealer dealer) {
			OptionView ov = new OptionView (human, dealer);
			ov.setBackground (FOREST_GREEN);
			getContentPane().add(ov, BorderLayout.SOUTH);
		}
		
		private BlackjackDealer getDealer() {
			if (dealer == null) {
				Hand dealer_hand = new Hand();
				Deckpile cards = getCards();
				dealer = new ThreadedBlackjackDealer("Dealer", dealer_hand, cards);
			}
				return dealer;
		}
		
		private GUIPlayer getHuman() {
			if(human == null) {
				Hand human_hand = new Hand();
				Bank bank = new Bank(1000);
				human = new GUIPlayer ("Human", human_hand, bank);
				
			}
				 return human;
		}
		 private Deckpile getCards() {
			 Deckpile cards = new Deckpile();
			 for( int i = 0; i <4; i++) {
				 cards.shuffle();
				 Deck deck = new VDeck();
				 deck.addToStack(cards);
				 cards.shuffle();
			 }
			 return cards;
		 }
	
}
