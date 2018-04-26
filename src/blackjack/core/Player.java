
package blackjack.core;

import java.util.ArrayList;
import java.util.Iterator;

import blackjack.ui.Console;
import blackjack.ui.mvc.PlayerView;
import blackjack.ui.pac.GUIPlayer.GUIView;

public abstract  class Player {
	
	private Hand hand;
	protected String name;
	private ArrayList listeners = new ArrayList ();
	private PlayerState current_state;
	
	public Player (String name, Hand hand) {
		
		this.name = name;
		this.hand = hand;
		setCurrentState (getInitialState());
	}
	
	public void addCard (Card card) {
		hand.addCard(card);
	}
	
	public void play(Dealer dealer) {
		current_state.execute(dealer);
	}
	
	public void reset () {
		hand.reset();
		setCurrentState (getInitialState());
			notifyChanged();
		}
		
		public void addListener(PlayerListener l) {
			listeners.add(l);
		}
		public String getName() {
			return name;
		}
		public String toString() {
			return (name + ": " + hand.toString());
		}
		
		public void win() {
			notifyWin();
		}
		public void lose() {
			notifyLose();
		}
		
		public void standoff() {
			notifyStandoff();
		}
		public void blackjack() {
			notifyBlackjack();
		}
		public Hand getHand() {
			return hand;
		}
		
		protected void notifyChanged() {
			Iterator i = listeners.iterator();
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerBusted(this);
			}  
		}
		
		protected void notifyBusted() {
			Iterator i = listeners.iterator();
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerBusted (this);
			}
		}
		
		protected void notifyBlackjack() {
			Iterator i = listeners.iterator();
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerBlackjack(this);
			}
		}
		
		protected void notifyStanding() {
			Iterator i = listeners.iterator();
			
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerStanding(this);
			}
		}
		protected void notifyStandoff() {
			Iterator i = listeners.iterator();
			
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerStandoff(this);
			}
		}
		protected void notifyWin() {
			Iterator i = listeners.iterator();
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerWon(this);
			}
		}
		
		protected void notifyLose() {
			Iterator i = listeners.iterator();
			while (i.hasNext()) {
				PlayerListener p1 = (PlayerListener) i.next();
				p1.playerLost(this);
			}
		}
		
		protected final void setCurrentState (PlayerState state) {
			current_state=state;
			hand.setHolder(state);
		}
		protected final PlayerState getCurrentState() {
			return current_state;
		}
		
		protected PlayerState getBustedState() {
			return new Busted();
		}
	
		protected PlayerState getStandingState() {
			return new Standing();
		}
		protected PlayerState getPlayingState() {
			return new Playing();
		}
		protected PlayerState getwaitingState() {
			return new Waiting();
		}
		protected PlayerState getBlackjackState() {
			return new Blackjack();
		}
		
		protected abstract PlayerState getInitialState();
		
		protected abstract boolean hit (Dealer dealer);
		
		private class Waiting implements PlayerState {
			public void handChanged() {
				notifyChanged();
			}
			
			public void handPlayable() {
				setCurrentState(getPlayingState());
				//transição
			}
			public void handBlackjack() {
				setCurrentState(getBlackjackState());
				notifyBlackjack();
			}
			
			public void hanBusted() {
				//impossivel no estado de espera
			}
			public void execute (Dealer dealer) {
				//não faz nada enquanto espera
			}

			@Override
			public void handBusted() {
				// TODO Auto-generated method stub
				
			}
		}
		
		private  class Busted implements PlayerState{
			public void handChanged() {
				//impossivel no estado do estouro	
			}
			public void handPlayable() {
				//impossivel no estado de estouto
			}
			public void handBlackjack() {
				//impossivel no estado de estouto
			}
			public void handBusted() {
				//impossivel no estado de estouro
			}
			public void execute(Dealer dealer) {
				dealer.busted(Player.this);
				//termina
			}
		}
		
		private class Blackjack implements PlayerState{
			public void handChanged() {
				//impossivel no estado vinte-e-um
			}
			public void handPlayable() {
				//impossivel no estado vinte-e-um
			}
			public void handBlackjack() {
				//impossivel no estado vinte-e-um
			}
			public void handBusted() {
				//impossivel no estado vinte-e-um
			}
			public void execute (Dealer dealer) {
				dealer.blackjack(Player.this);
				//termina
			}
		}
		
		private class Standing implements PlayerState{
			public void handChanged() {
				//impossivel no estado de parada
			}
			public void handPlayable() {
				//impossivel no estado de parada
			}
			public void handBlackjack() {
				//impossivel no estado de parada
			}
			public void handBusted() {
				//impossivel no estado de parada
			}
			public void execute (Dealer dealer) {
				dealer.standing(Player.this);
				//termina
			}
		}
		
		private class Playing implements PlayerState{
			public void handChanged() {
				notifyChanged();
			}
			public void handPlayable() {
				//pode ignorar no estado de jogo
			}
			public void handBlackjack() {
				//impossivel no estado de jogo
			}
			public void handBusted() {
				setCurrentState(getBustedState());
				notifyBusted();
			}
			public void execute(Dealer dealer) {
				if(hit(dealer)) {
					dealer.hit(Player.this);
				} else {
					setCurrentState(getStandingState());
					notifyStanding();
				}
				current_state.execute(dealer);
				//transição
			}
		}

		public void addListener(Console instance) {
			// TODO Auto-generated method stub
			
		}

		public void addListener(PlayerView playerView) {
			// TODO Auto-generated method stub
			
		}
		
	}

