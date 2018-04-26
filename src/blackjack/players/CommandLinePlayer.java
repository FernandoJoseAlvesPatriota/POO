package blackjack.players;

import blackjack.core.*;
import blackjack.ui.*;

public class CommandLinePlayer extends BettingPlayer {
	
	private final static String HIT = "H";
	private final static String STAND = "S";
	private final static String PLAY_MSG = "[H]IT OU [S]stay";
	private final static String BET_MSG = "Place Bet:[10] [50] or [100]";
	private final static String DD_MSG = "Double Down? [Y]es [N]o";
	private final static String BET_10 = "10";
	private final static String BET_50 = "50";
	private final static String BET_100 = "100";
	private final static String NO = "N";
	private final static String YES = "Y";
	private final static String DEFAULT = "invalid";
	
	public CommandLinePlayer (String name, Hand hand, Bank bank) {
		super(name, hand, bank);
	}
	
	protected boolean hit(Dealer dealer) {
		while(true) {
			Console.INSTANCE.printMessage(PLAY_MSG);
			String response = Console.INSTANCE.readInput(DEFAULT);
				if(response.equalsIgnoreCase(HIT)) {
					return true;
				} else if (response.equalsIgnoreCase(STAND)) {
					return false;
				}
				//se chegamos até aqui, faz laço até obtermos uma entrada significativa
		}
	}
	
		protected boolean doubleDown (Dealer dealer) {
			while (true) {
				Console.INSTANCE.printMessage (DD_MSG);
				String response = Console.INSTANCE.readInput(DEFAULT);
					if (response.equalsIgnoreCase(YES)) {
						return true;
					}
					//se chegamos até aqui, faz laço até obtermos uma entrada significativa	
			}
		}
		
		protected void bet() {
			while (true) {
				Console.INSTANCE.printMessage(BET_MSG);
				String responde = Console.INSTANCE.readInput(DEFAULT);
					Object response = null;
					if (response.equals(BET_10)) {
						getBank().place10Bet();
						return;
					}
					if(response.equals(BET_50)) {
						getBank().place50Bet();
						return;
					}
					if(response.equals(BET_100)) {
						getBank().place100Bet();
						return;
					}
					////se chegamos até aqui, faz laço até obtermos uma entrada significativa
			}
		}
}
