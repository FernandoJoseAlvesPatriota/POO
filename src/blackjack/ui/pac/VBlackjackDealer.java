package blackjack.ui.pac;

import blackjack.core.*;
import blackjack.core.threaded.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class VBlackjackDealer extends ThreadedBlackjackDealer implements Displayable {
	
	private DealerView view;
	public GUIPlayer addPlayer;
	
	public VBlackjackDealer (String name,VHand hand, Deckpile cards) {
		super(name, hand, cards);
	}
	
	public JComponent view () {
		if (view == null) {
			view = new DealerView((VHand)getHand());
			addListener (view);
		}
		
		return view;
	}
	
	//Note que tudo que essa classe faz é recuperar o modo de visualização de Hand, adiciona esse modo
	//em si mesmo e atualiza a borda conforme for necessário. Note o que essa classe não faz:
	// atualiza as cartas quando elas mudam. Do ponto de vista desse modo de visuaização,
	// a atualização da carta acontece automaticamente, pois VHand atualiza sua exibição
	// nos bastidores
	
	private class DealerView extends JPanel implements PlayerListener{
		private TitledBorder border;
		
		public DealerView(VHand hand) {
			super(new FlowLayout(FlowLayout.LEFT));
			String name = VBlackjackDealer.this.getName();
			border = new TitledBorder(name);
			setBorder(border);
			setBackground(new Color (35,142,35));
			border.setTitleColor(Color.black);
			add(hand.view() );
			repaint();
		}
		
		public void PlayerChanged (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name);
			repaint();
		}

		public void PlayerBusted (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name + "BUSTED!");
			repaint();
		}

		public void PlayerBlackjack (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name + "BLACKJACK!");
			repaint();
		}
		
		public void PlayerStanding (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name + "STANDING!");
			repaint();
		}
		public void PlayerWon (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name + "WINNER!");
			repaint();
		}
		public void PlayerLost (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name + "LOSER!");
			repaint();
		}
		
		public void playerStandoff (Player player) {
			String name = VBlackjackDealer.this.getName();
			border.setTitle(name + "STANDOFF!");
			repaint();
		}

		@Override
		public void playerChanged(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void playerBusted(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void playerBlackjack(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void playerStanding(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void playerWon(Player player) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void playerLost(Player player) {
			// TODO Auto-generated method stub
			
		}

		
	}

}
