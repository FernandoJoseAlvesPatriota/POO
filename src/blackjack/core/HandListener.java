package blackjack.core;

public interface HandListener {
	
	public void handPlayable();
	public void handBlackjack();
	public void handBusted();
	public void handChanged();

}
