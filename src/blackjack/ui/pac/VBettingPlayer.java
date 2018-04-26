package blackjack.ui.pac;

import blackjack.core.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public abstract class VBettingPlayer extends BettingPlayer implements Displayable {
	
	private BettingView view;
	
	public VBettingPlayer(String name, VHand hand, Bank bank) {
		super(name, hand, bank);
	}
	
	public JComponent view() {
		if(view == null) {
			view = new BettingView((VHand) getHand());
			addListener(view);
		}
		
		return view;
	}



	//Note que tudo que essa classe faz é recuperar o modo de visualização de Hand, adiciona esse modo
	// em si mesmo e atualiza a borda, conforme for necessário. Note o que essa classe não faz:
	// atualiza as cartas quando elas mudam. Do ponto de vista deste modo de visualização, a
	// atualização da carta acontece auutomaticamente, pos VHand atualiza sua exibição nos bastidores

	private class BettingView extends JPanel implements PlayerListener {
		
		private TitledBorder border;
		
		public BettingView(VHand hand) {
			super(new FlowLayout (FlowLayout.LEFT));
			buildGUI (hand.view());
		}
		
		public void playerChanged(Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name);
			repaint();
		}
		
		public void playerBusted(Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name + "BUSTED");
			repaint();
		}
		public void playerBlackjack(Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name + "BLACKJACK");
			repaint();
		}
		public void playerStanding(Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name + "STANDING");
			repaint();
		}
		public void playerWon(Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name + "WINNER!");
			repaint();
		}
		public void playerLost(Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name + "LOSER!");
			repaint();
		}
		
		public void playerStandoff (Player player) {
			String name = VBettingPlayer.this.getName();
			border.setTitle(name + "STANDOFF!");
			repaint();
		}
		public void buildGUI(JComponent hand) {
			border = new TitledBorder (VBettingPlayer.this.getName());
			setBorder (border);
			setBackground (new Color (35,142, 35));
			add(hand);
		}
		
		
	}
	
}
