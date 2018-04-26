package blackjack.core;

import java.util.ArrayList;
import java.util.Iterator;

public class BlackjackDealer extends Player implements Dealer {
	
	private Deckpile cards;
	private ArrayList players = new ArrayList();
	
	protected ArrayList waiting_players;
	protected ArrayList betting_players;
	private ArrayList standing_players;
	private ArrayList busted_players;
	private ArrayList blackjack_players;
	
	public BlackjackDealer (String name, Hand hand, Deckpile cards) {
		super(name, hand);
		this.cards = cards;
	}

		//Métodos que os jogadores podem chamar 
	public void blackjack (Player player) {
		blackjack_players.add(player);
		play(this);
	}
	
	public void busted (Player player) {
		busted_players.add(player);
		play(this);
	}
	
	public void standing (Player player) {
		standing_players.add(player);
		play(this);
	}
	
	 public void doneBetting (Player player) {
		 waiting_players.add(player);
		 play(this);
	 }
	 
	 public void hit (Player player) {
		 player.addCard(cards.dealUp());
	 }
	 
	 public Card getUpCard() {
		 Iterator i = getHand().getCards();
		 	while(i.hasNext()) {
		 		Card card = (Card) i.next();
		 		if (card.isFaceUp( )) {
		 			return card;
		 		}
		 	}
		 	
		 	//não deve chegar aqui
		 	return null;
	 }
	 //Métodos de configuração do jogo
	 public void addPlayer (Player player) {
		 players.add(player);
	 }
	 
	 public void reset() {
		 super.reset();
		 
		 //configura os baldes do jogador
		 waiting_players = new ArrayList();
		 standing_players = new ArrayList();
		 busted_players = new ArrayList();
		 blackjack_players = new ArrayList();
		 betting_players = new ArrayList();
		 betting_players.addAll(players);
		 
		 cards.reset();
		 Iterator i = players.iterator();
		 while (i.hasNext()) {
			 Player player = (Player) i.next();
			 player.reset();
		 }
		}
	 
	 	public void newGame() {
	 		reset();
	 		//vai!
	 		play(this);
	 	}
	 	
	 	public void deal() {
	 		
	 		cards.shuffle();
	 		//reconfigura cada jogador e distribui uma carta aberta para cada um e para si mesma
	 		Player [] player = new Player [waiting_players.size()];
	 		waiting_players.toArray(player);
	 		for (int i = 0; i < player.length; i ++) {
	 			player [i].addCard (cards.dealUp());
	 		}
	 		this.addCard(cards.dealUp());
	 		
	 		//distribui mais uma carta aberta para cada jogador e uma fechada para si mesma
	 		for (int i=0; i < player.length; i ++) {
	 			player[i].addCard(cards.dealUp());
	 		}
	 		this.addCard(cards.dealDown());
	 	}
	 	
	 	protected boolean hit (Dealer dealer) {
	 		if(standing_players.size() >0 && getHand().total() <17) {
	 				return true;
	 		}
	 		return false;	 		
	 	}
	 	
	 	protected void exposeHand() {
	 		getHand().turnOver();
	 		notifyChanged();
	 	}
	 	
	 	protected PlayerState getBlackjackState() {
	 		return new DealerBlackjack();	
	 	}
	 	protected PlayerState getDealingState() {
	 		return new DealerDealing();
	 	}
	 	protected PlayerState getCollectingBetsState() {
	 		return new DealerCollectingBets();
	 	}
	 	protected PlayerState getBustedState() {
	 		return new DealerBusted();
	 	}
	 	protected PlayerState getStandingState() {
	 		return new DealerStanding();
	 	}
	 	protected PlayerState getWaitingState() {
	 		return new DealerWaiting();
	 	}
	 	protected PlayerState getInitialState() {
	 		return new DealerCollectingBets();
	 	}
	 	
	 	private class DealerCollectingBets implements PlayerState{
	 		public void handChanged() {
	 			//impossiveel no estado de aposta
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
	 		
	 		public void execute(Dealer dealer) {
	 			if (!betting_players.isEmpty()) {
	 				Player player = (Player)betting_players.get(0);
	 				betting_players.remove(player);
	 				player.play(dealer);
	 				
	 			} else {
	 				setCurrentState(getDealingState());
	 				getCurrentState().execute(dealer);
	 				//faz a transiçao e executa
	 			}
	 		}
	 	}
	 	
	 	private class DealerBusted implements PlayerState {
	 		public void handChanged() {
	 			//impossivel no estado de estouro
	 		}
	 		public void handPlayable() {
	 			//impossivel no estado de estouro
	 		}
	 		public void handBlackjack() {
	 			//impossivel no estado de estouro
	 		}
	 		public void handBusted() {
	 			//impossivel no estado de estouro
	 		}
	 		public void execute(Dealer dealer) {
	 			Iterator i = standing_players.iterator();
	 			while(i.hasNext()) {
	 				Player player = (Player) i.next();
	 				player.win();
	 			}
	 			i = blackjack_players.iterator();
	 			while (i.hasNext()) {
	 				Player player = (Player) i.next();
	 				player.blackjack();	
	 			}
	 			i = busted_players.iterator();
	 			while (i.hasNext()) {
	 				Player player = (Player) i.next();
	 				player.lose();
	 			}
	 		}
	 	}
	 	
	 		private class DealerBlackjack implements PlayerState {
	 			public void handChanged() {
	 				notifyChanged();
	 			}
	 			
	 		public void handPlayer() {
	 			//impossivel no estado de vinte-e-um
	 		}
	 		
	 		public void handBlackjack() {
	 			//impossivel no estado de vinte-e-um
	 		}
	 		public void handBusted() {
	 			//impossivel no estado de vinte-e-um
	 		}
	 		public void execute (Dealer dealer) {
	 			exposeHand();
	 			Iterator i = players.iterator();
	 			while (i.hasNext()) {
	 				Player player = (Player) i.next();
	 				if(player.getHand().blackjack()) {
	 					player.standoff();
	 				} else {
	 					player.lose();
	 				}
	 			}
	 		}

			@Override
			public void handPlayable() {
				// TODO Auto-generated method stub
				
			}
	 	}
	 		private class DealerStanding implements PlayerState{
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
	 			
	 			public void execute(Dealer dealer) {
	 				Iterator i = standing_players.iterator();
	 				while (i.hasNext()) {
	 					Player player = (Player) i.next();
	 					if(player.getHand().isEqual(getHand())) {
	 						player.standoff();
	 					} else if(player.getHand().isGreaterThan(getHand())) {
	 						player.win();
	 					} else {
	 						player.lose();
	 					}
	 				}
	 					
	 			i = blackjack_players.iterator();
	 			while (i.hasNext()) {
	 				Player player = (Player) i.next();
	 				player.blackjack();
	 			}
	 			i = busted_players.iterator();
	 			while (i.hasNext()) {
	 				Player player = (Player) i.next();
	 				player.lose();
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
	 				public void hanBusted() {
	 					//impossivel no estado de espera
	 				}
	 				
	 				public void execute (Dealer dealer) {
	 					if (!waiting_players.isEmpty()) {
	 						Player player = (Player) waiting_players.get(0);
	 						waiting_players.remove(player);
	 						player.play(dealer);
	 					} else {
	 						setCurrentState (getPlayingState());
	 						exposeHand();
	 						getCurrentState().execute(dealer);
	 						//faz a transição e executa
	 					}
	 				}
					@Override
					public void handBlackjack() {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void handBusted() {
						// TODO Auto-generated method stub
						
					}
	 			}
	 			
	 			private class DealerDealing implements PlayerState{
	 				public void handChanged() {
	 					notifyChanged();
	 				}
	 				
	 				public void handPlayable() {
	 					setCurrentState(getwaitingState());
	 					//transição
	 					
	 				}
	 				public void handBlackjack() {
	 					setCurrentState(getwaitingState());
	 					notifyBlackjack();
	 					//transição
	 				}
	 				public void handBusted() {
	 					//impossivel no estado de distribuição
	 				}
	 				
	 				public void execute (Dealer dealer) {
	 					deal();
	 					getCurrentState().execute(dealer);
	 					//faz a transição e executa
	 				}
	 			}		
	 			
	 	}
	 	
	 