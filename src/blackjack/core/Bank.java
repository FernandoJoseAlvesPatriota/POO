package blackjack.core;

public class Bank {

	private int total;
	private int bet;
	
	public Bank(int amount) {
		total = amount;
	}
	
	public void doubleDown() {
		placeBet(bet);
		bet = bet * 2;
	}
	
	public void place100Bet() {
		placeBet(100);
	}
	
	public void place50Bet() {
		placeBet(50);
	}
	
	public void place10Bet() {
		placeBet(10);
	}
	
	public void lose() {
		//já extraído de total
		bet = 0;
	}
	
	public void blackjack() {
		total += (((3 * bet) / 2 ) + bet);
		bet = 0;
	}
	
	public void standoff() {
		total += bet;
		bet = 0;
	}
	
	public String toString() {
		return("$" + total + ".00");
	}
	
	private void placeBet(int amount) {
		bet = amount;
		total -= amount;
		
	}

	public void win() {
		// TODO Auto-generated method stub
		
	}
}

