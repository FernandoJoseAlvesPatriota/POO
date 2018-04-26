package blackjack.core;
public interface Dealer {
	//usado pelo jogador para interagir com a banca
	public void hit (Player player);
	
	//usado pelo jogador para comunicar estado a banca
	public void blackjack(Player player);
	public void busted(Player player);
	public void standing(Player player);
	public void doneBetting(Player player);
	
	public Card getUpCard();	
}

