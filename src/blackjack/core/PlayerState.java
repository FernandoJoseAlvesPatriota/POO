package blackjack.core;

public interface PlayerState extends HandListener {
	
	public void execute(Dealer dealer);
}
