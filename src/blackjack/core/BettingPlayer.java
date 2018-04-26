package blackjack.core;

public abstract class BettingPlayer extends Player {
	
	private Bank bank;
	
	public BettingPlayer (String name, Hand hand, Bank bank) {
		super (name,hand);
		this.bank = bank;
	}
	
	//comportamento sobreposto
	public String toString() {
		return ( super.getName() + ": " + getHand().toString() + "\n" + bank.toString());
	}
	
	public String getName() {
		return (super.getName() + " " + bank.toString() );
	}
	public void win () {
		bank.win();
		super.win();
	}
	public void lose () {
		bank.lose();
		super.lose();
	}
	public void standoff() {
		bank.standoff();
		super.standoff();
	}
	public void blackjack() {
		bank.blackjack();
		super.blackjack();
	}
	protected PlayerState getInitialState() {
		return getBettingState();
	}
	private PlayerState getBettingState() {
		// TODO Auto-generated method stub
		return null;
	}

	protected PlayerState getPlayingState() {
		return new BetterPlaying();
	}
	
	//adicionando recentemente para BettingPlayer
	protected final Bank getBank() {
		return bank;
	}
	protected PlayerState getDoublingDownState() {
		return new DoublingDown();
	}
	
	protected abstract void bet();
	protected abstract boolean doubleDown( Dealer dealer);
	
	private class Betting implements PlayerState {
		public void handChanged() {
			//impossivel no estado de estouro 
		}
		public void handPlayable () {
			//impossivel no estado de estouro
		}
		public void handBlackjack() {
			//impossivel no estado de estouro
		}
		public void handBusted() {
			//impossivel no estado de estouro
		}
		public void execute (Dealer dealer) {
			bet();
			setCurrentState (getwaitingState());
			dealer.doneBetting (BettingPlayer.this);
			//termina
		}
		}
	private class DoublingDown implements PlayerState{
		public void handChanged() {
			notifyChanged();
		}
	public void handPlayable() {
		setCurrentState (getStandingState() );
		notifyStandoff();
	}
	public void handBlackJack() {
		//impossivel no estado de dobro
	}
	public void hanBusted() {
		setCurrentState ( getBustedState() );
		notifyBusted();
	}
	public void execute (Dealer dealer) {
		bank.doubleDown();
		dealer.hit (BettingPlayer.this);
		getCurrentState().execute(dealer);
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
	private class BetterPlaying implements PlayerState{
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
			setCurrentState( getBustedState() );
			notifyBusted();
		}
		public void execute (Dealer dealer) {
			if (getHand().canDoubleDown() && doubleDown(dealer)) {
				setCurrentState( getDoublingDownState() );
				getCurrentState().execute(dealer);
				return;
			}
			if (hit(dealer)) {
				dealer.hit(BettingPlayer.this);
			} else {
				setCurrentState(getStandingState());
				notifyStanding();
			}
			getCurrentState().execute(dealer);
			//transição
		}
	}
}
		
	

