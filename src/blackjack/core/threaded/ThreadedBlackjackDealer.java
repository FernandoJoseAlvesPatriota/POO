package blackjack.core.threaded;

import blackjack.core.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ThreadedBlackjackDealer extends BlackjackDealer {
	
	public ThreadedBlackjackDealer (String name, Hand hand, Deckpile cards) {
			super(name, hand, cards);
	}
	
	protected PlayerState getWaitingState() {
		return new DealerWaiting();
	}
	protected PlayerState getCollectingBetsState() {
		return new DealerCollectingBets();
}
	private class DealerCollectingBets implements PlayerState{
		public void handChanged() {
			//impossivel no estado de aposta
		}
		public void handPlayable() {
			//impossivel no estado de aposta 
		}
		public void handBlackjack() {
			//impossivel no estado de aposta
		}
		public void handBusted() {
			//impossivel no estado de aposta
		}
		public void execute (final Dealer dealer) {
			if(!betting_players.isEmpty()) {
				final Player player = (Player) betting_players.get(0);
				betting_players.remove(player);
				Runnable runnable = new Runnable() {
					public void run() {
						player.play(dealer);
					}
				};
				Thread threaded = new Thread (runnable);
				threaded.start();
			} else {
				setCurrentState(getDealingState());
				getCurrentState().execute(dealer);
				//faz a transição e executa
			}
		}
	}
	
		private class DealerWaiting implements PlayerState{
			public void handChanged() {
				//impossivel no estado de espera
			}
			public void handPlayable() {
				//impossivel no estado de espera
			}
			public void handBlackjack() {
				//impossivel no estado de espera
			}
			public void handBusted() {
				//impossivel no estado de espera
			}
			
			public void execute(final Dealer d) {
				if(!waiting_players.isEmpty()) {
					final Player p = (Player) waiting_players.get(0);
					waiting_players.remove(p);
					Runnable r = new Runnable() {
						public void run() {
							p.play(d);
						}
					};
					Thread t = new Thread(r);
					t.start();
				} else {
					setCurrentState(getPlayingState());
					exposeHand();
					getCurrentState().execute(d);
					//faz a transição e executa
					
				}
			}
		}
}