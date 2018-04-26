package blackjack.ui.pac;

import blackjack.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIPlayer extends VBettingPlayer implements Displayable {

	private BlackjackDealer dealer;
	private JPanel view;
	
	public GUIPlayer( String name, VHand hand, Bank bank, VBlackjackDealer dealer ) {
		
		super(name,hand,bank);
		this.dealer = dealer;
	}
	
	public boolean hit(Dealer dealer) {
		return true;
	}
	
	public void bet() {
		
		//não faz nada, isto será chamado
		//em vez disso, o jogador humano presiona um boirão da GUI
		
	}
	
	//esses métodos de aposta serão chamdados pelo controlador da GUI
	//para cada um: faz a aposta correta, mudar o estado, permite que a
	//canca saiba que o jogo terminou de apostar 
	
	public void place10Bet() {
		
		getBank().place10Bet();
		setCurrentState( getwaitingState());
		dealer.doneBetting(this);
		
	}
	
	public void place50Bet() {
		
		getBank().place50Bet();
		setCurrentState( getwaitingState());
		dealer.doneBetting(this);
	
	}
	
	public void place100Bet() {
		
		getBank().place100Bet();
		setCurrentState( getwaitingState());
		dealer.doneBetting(this);
	}
	
	//a aposta em dobro é um pouco diferente,pois o jogador precissa
	//responder aos eventos de Hand quando uma carta é adicionada na mão
	//de modo que configura o estado como Doubling e depois o executa
	protected boolean boubleDown(Dealer d) {
		setCurrentState( getDoublingDownState());
		getCurrentState().execute(dealer);
		return true;
	}
	//takeCard será chamado pelo controlador da GUI quando o jogador
	//decidir receber mais cartas
	public void takeCard() {
		dealer.hit(this);
	}
	
	//stand será chamado pelo controlador da GUI quando o jogador optar
	// por parar, quando a paeada mudar de estado, deixa o mundo saber, e depois 
	// diz á banca
	
	public void stand() {
		setCurrentState(getStandingState());
		notifyStanding();
		getCurrentState().execute(dealer);
	}
	
	public JComponent view() {
		if(view == null) {
			view = new JPanel (new BorderLayout());
			JComponent pv = super.view();
			GUIView cv = new GUIView();
			addListener(cv);
			view.add(pv, BorderLayout.CENTER);
			view.add(cv, BorderLayout.SOUTH);
		}
		
		return view;
	}
	// o seguinte trata dos estados
	protected PlayerState geyPlayingState() {
		return new Playing();
	}
	protected PlayerState getBettingState() {
		return new Betting();
	}
	
	private class Playing implements PlayerState{
		public void handPlayable() {
			//não faz nada
		}
		
		public void handBlackjack() {
			setCurrentState (getBlackjackState());
			notifyBlackjack();
			getCurrentState().execute(dealer);
		}
		public void handBusted () {
			setCurrentState(getBustedState());
			notifyBusted();
			getCurrentState().execute(dealer);
		}
		public void handChanged() {
			notifyChanged();
		}
		
		public void execute (Dealer dealer) {
			//não faz nada aqui, as ações virão da GUI, que é
			//externa ao estado, mas quando eventos vierem, certifica-se
			//forçar a transição de estado imediatament
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
			public void Busted() {
				//impossivel no estado de estouro
			}
			
			public void execute(Dealer dealer) {
				//não faz nada aqui, as ações virão da GUI, que é
				// externa ao estado, pois nenhum evento aparece como parte da 
				// aposta, o estado precisará ser mudado externamente para este estado
				}
			@Override
			public void handBusted() {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		public class GUIView extends JPanel implements PlayerListener, ActionListener{
			
			private JButton bet_10 = new JButton("$10");
			private JButton bet_50 = new JButton("$50");
			private JButton bet_100 = new JButton("$100");
			private JButton deal = new JButton("New Game");
			private JButton quit = new JButton("Quit");
			private JButton hit = new JButton("Hit");
			private JButton stand = new JButton("Stand");
			private JButton ddwon = new JButton("Double Down");
			 
			private final String NEW_GAME ="new";
			private final String QUIT ="quit";
			private final String HIT ="hit";
			private final String STAND ="stand";
			private final String BET_10 ="BET10";
			private final String BET_50 ="BET50";
			private final String BET_100 ="BET100";
			private final String D_DOWN ="DDown";
			
			
			private final Color FOREST_GREEN = new Color (35,142,35);
			
			public GUIView() {
				super(new BorderLayout());
				GUIPlayer.this.addListener(this);
				buildGUI();
			}
			
			private void buildGUI() {
				JPanel betting_controls = new JPanel();
				JPanel game_controls = new JPanel();
				
				add (betting_controls, BorderLayout.NORTH);
				add(game_controls, BorderLayout.SOUTH);
				
				
				betting_controls.setBackground(FOREST_GREEN);
				game_controls.setBackground(FOREST_GREEN);
				deal.setActionCommand(NEW_GAME);
				deal.addActionListener(this);
				quit.setActionCommand(QUIT);
				quit.addActionListener(this);
				hit.setActionCommand(HIT);
				hit.addActionListener(this);
				stand.setActionCommand(STAND);
				stand.addActionListener(this);
				bet_10.setActionCommand(BET_10);
				bet_10.addActionListener(this);
				bet_50.setActionCommand(BET_50);
				bet_50.addActionListener(this);
				bet_100.setActionCommand(BET_100);
				bet_100.addActionListener(this);
				ddwon.setActionCommand(D_DOWN);
				ddwon.addActionListener(this);
				betting_controls.add(bet_10);
				betting_controls.add(bet_50);
				betting_controls.add(bet_100);
				game_controls.add(ddwon);
				game_controls.add(hit);
				game_controls.add(stand);
				game_controls.add(deal);
				game_controls.add(quit);
				enableBettingControls(false);
				enablePlayerControls(false);
				enableDoubleDown(false);
				
			}
			private void enableBettingControls(boolean enable) {
				bet_10.setEnabled(enable);
				bet_50.setEnabled(enable);
				bet_100.setEnabled(enable);
			
				
			}
			private void enablePlayerControls(boolean enable) {
				hit.setEnabled(enable);
				stand.setEnabled(enable);
			}
			
			private void enableGameControls(boolean enable) {
				deal.setEnabled(enable);
				quit.setEnabled(enable);
			}
			private void enableDoubleDown(boolean enable) {
				ddwon.setEnabled(enable);
			}
			
			public void actionPerformed (ActionEvent event) {
				if(event.getActionCommand().equals(QUIT)) {
					System.exit(0);
				} else if (event.getActionCommand().equals(HIT)) {
					enableDoubleDown(false);
					takeCard();
				} else if (event.getActionCommand().equals(STAND)) {
					enableDoubleDown(false);
					stand();
				} else if (event.getActionCommand().equals(NEW_GAME)) {
					enableDoubleDown (false);
					enableGameControls(false);
					enablePlayerControls(false);
					enableBettingControls(true);
					dealer.newGame();
					
				} else if (event.getActionCommand().equals(BET_10)) {
					enableDoubleDown(true);
					enableBettingControls(false);
					enablePlayerControls(true);
					place10Bet();
				} else if (event.getActionCommand().equals(BET_50)) {
					enableDoubleDown(true);
					enableBettingControls(false);
					enablePlayerControls(true);
					place50Bet();
				} else if  (event.getActionCommand().equals(BET_100)) {
					enableDoubleDown(true);
					enableBettingControls(false);
					enablePlayerControls(true);
					place100Bet();
				} else if (event.getActionCommand().equals(D_DOWN)) {
					enablePlayerControls(false);
					enableDoubleDown(false);
					doubleDown(dealer);
				}
}
			public void playerChanged(Player player) {}
			
			public void playerBusted(Player player)	{
				enablePlayerControls(false);
				enableGameControls(true);
			}
			public void playerBlackjack(Player player)	{
				enableDoubleDown(false);
				enablePlayerControls(false);
				enableGameControls(true);
			}
			public void playerStanding (Player player)	{
				enablePlayerControls(false);
				enableGameControls(true);
			}
			public void playerWon(Player player)	{
				enablePlayerControls(false);
				enableGameControls(true);
			}
			public void playerLost(Player player)	{
				
				enableDoubleDown(false);
				enablePlayerControls(false);
				enableGameControls(true);
			}
			public void playerStandoff(Player player)	{
				
				enablePlayerControls(false);
				enableGameControls(true);
			}
				
			}

		@Override
		protected boolean doubleDown(Dealer dealer) {
			// TODO Auto-generated method stub
			return false;
		}
}
