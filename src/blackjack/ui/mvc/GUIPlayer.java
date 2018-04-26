package blackjack.ui.mvc;

import blackjack.core.*;

public class GUIPlayer extends BettingPlayer {
	
	private Dealer dealer;
	
	public GUIPlayer (String name, Hand hand, Bank bank) {
		super(name, hand, bank);
	}
	
	public boolean hit (Dealer dealer) {
		return true;
	}
	
	public void bet () {
		//não faz nada, isto não será chamado
		//em vez disso, o jogador humano pressiona um botão da GUI
}
	
	//esses métodods de aposta serão chamados pelo controlador da GUI
	//para cada um: faz a aposta correta, muda o estado, permite que a 
	//banca saiba que p jogador terminou de apostar
	public void place10Bet() {
		getBank().place10Bet();
		setCurrentState(getwaitingState());
		dealer.doneBetting(this);
	}
	
	public void place50Bet() {
		getBank().place50Bet();
		setCurrentState(getwaitingState());
		dealer.doneBetting(this);
	}
	public void place100Bet() {
		getBank().place10Bet();
		setCurrentState(getwaitingState());
		dealer.doneBetting(this);
	}
	//a aposta em dobro é um pouco diferente, pois o jogador precisa 
	//responder aos eventos de Hand quando uma carta é adicionada na mão
	// de modo que configura o estado como DoublingDown e depois o executa
	
	protected boolean doubleDown (Dealer d) {
		setCurrentState (getDoublingDownState());
		getCurrentState().execute(dealer);
		return true;
	}
	
	//takeCard será chamado pelo controlador da GUI quando o jogados
	//decidir receber mais cartas
	public void takeCard() {
		dealer.hit(this);
		}
	//stand será chamado pelo controlador da GUI quando o jogador optar
	//por parar, quando a parada mudar de estado, deixa o mundo saber, e depois
	//diz á banca
	public void stand() {
		setCurrentState(getStandingState());
		notifyStanding();
		getCurrentState().equals(dealer);
	}

	//você precisa sobrepor play para que ele armazene a banca para 
	//uso posterior
	public void play (Dealer dealer) {
		this.dealer = dealer;
		super.play(dealer);
	}
		
	 	//o seguinte trata dos estados
	protected PlayerState getPlayingState() {
	return new Playing();
	}
	
	protected PlayerState getBettingState() {
		return new Betting();
	}
	private class Playing implements PlayerState{
		
		public void handPlayable() {
			//não faz nada
		}
		
		public void hanBlackjack() {
			setCurrentState(getBlackjackState());
			notifyBlackjack();
			getCurrentState().execute(dealer);
		}
		
		public void hanBusted() {
			setCurrentState(getBustedState());
			hanBusted();
			getCurrentState().execute(dealer);
		}
		public void handChanged() {
			notifyChanged();
			
		}
		public void execute(Dealer dealer) {
			//não faz nada aqui, as ações virão da GUI, que é
			//externa ao estado, mas quando eventos vierem, certifica-se
			// de forçar a transição de estado imediatamente
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
	
		private class Betting implements PlayerState{
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
				//não faz nada aqui, as ações virão da GUI, que é 
				// externa ao estado, pois nenhum evento aparece como parte da
				//aposta, o estado precisará ser mudado extermamente para este estado
		
			}	
			
		}

		public void addListener(OptionViewController optionViewController) {
			// TODO Auto-generated method stub
			
		}
}