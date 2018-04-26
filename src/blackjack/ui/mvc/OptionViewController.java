package blackjack.ui.mvc;

import blackjack.core.*;
import java.awt.event.*;

public class OptionViewController implements ActionListener, PlayerListener {
	private GUIPlayer model;
	private OptionView view;
	private BlackjackDealer dealer;
	
	public OptionViewController (GUIPlayer model, BlackjackDealer dealer, OptionView view) {
		this.model = model;
		model.addListener(this);
		this.dealer = dealer;
		this.view = view;
		view.enablePlayerControls(false);
	}
	
	public void actionPerformed (ActionEvent event) {
		if (event.getActionCommand().equals(OptionView.QUIT)) {
			System.exit(0);
		} else if (event.getActionCommand().equals(OptionView.HIT)) {
			view.enableDoubleDown(false);
			model.takeCard();
		} else if (event.getActionCommand().equals(OptionView.STAND)) {
			view.enableDoubleDown(false);
			model.stand();
		} else if (event.getActionCommand().equals(OptionView.NEW_GAME)) {
			view.enableDoubleDown(false);
			view.enableGameControls(false);
			view.enablePlayerControls(false);
			view.enableBettingControls(true);
			dealer.newGame();
		} else if (event.getActionCommand().equals(OptionView.BET_10)) {
			view.enableBettingControls(false);
			view.enablePlayerControls(true);
			view.enableDoubleDown(true);
			model.place10Bet();
		} else if (event.getActionCommand().equals(OptionView.BET_50)) {
			view.enableBettingControls(false);
			view.enablePlayerControls(true);
			view.enableDoubleDown(true);
			model.place50Bet();
		} else if (event.getActionCommand().equals(OptionView.BET_100)) {
			view.enableBettingControls(false);
			view.enablePlayerControls(true);
			view.enableDoubleDown(true);
			model.place100Bet();
		} else if (event.getActionCommand().equals(OptionView.DOUBLE_DOWN)) {
			view.enableBettingControls(false);
			view.enablePlayerControls(false);
			view.enableDoubleDown(false);
			view.enableGameControls(true);
			model.doubleDown(dealer);
		}
	}
	
	public void playerChanged( Player player) {}
	
	public void playerBlackjack(Player player) {
		view.enablePlayerControls(false);
		view.enableDoubleDown(false);
		view.enableGameControls(true);
	}
	
	public void playerStantind(Player player) {
		view.enablePlayerControls(false);
		view.enableGameControls(true);
	}
	
	public void playerWon (Player player) {
		view.enablePlayerControls(false);
		view.enableGameControls(true);
	}
	public void playerLost(Player player) {
		view.enablePlayerControls(false);
		view.enableDoubleDown(false);
		view.enableGameControls(true);
	}
	public void playerStandodd(Player player) {
		view.enablePlayerControls(false);
		view.enableGameControls(true);
	}

	@Override
	public void playerBusted(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerStanding(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerStandoff(Player player) {
		// TODO Auto-generated method stub
		
	}
}