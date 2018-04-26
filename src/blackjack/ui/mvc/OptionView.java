package blackjack.ui.mvc;

import blackjack.core.*;
import javax.swing.*;
import java.awt.*;

public class OptionView extends JPanel{
	
	public static final String NEW_GAME = "new";
	public static final String QUIT = "quit";
	public static final String HIT = "hit";
	public static final String STAND = "stand";
	public static final String BET_10 = "BET10";	
	public static final String BET_50 = "BET50";
	public static final String BET_100 = "BET100";	
	public static final String DOUBLE_DOWN = "dd";
	
	private JButton bet_10 = new JButton("$10");
	private JButton bet_50 = new JButton("$50");
	private JButton bet_100 = new JButton("$100");
	private JButton deal = new JButton("New Game");
	private JButton quit = new JButton("Quit");
	private JButton hit = new JButton("Hit");
	private JButton stand = new JButton("Stand");
	private JButton ddown = new JButton("Double Down");
	private BlackjackDealer dealer;
	private GUIPlayer player;
	
	private static final Color FOREST_GREEN = new Color (35,142,35);
	
	public OptionView(GUIPlayer player, BlackjackDealer dealer) {
		super(new BorderLayout());
		this.player = player;
		this.dealer = dealer;
		attachController(makeController());
		buildGUI();
	}
	
	public void attachController(OptionViewController controller) {
		deal.addActionListener(controller);
		quit.addActionListener(controller);
		hit.addActionListener(controller);
		stand.addActionListener(controller);
		bet_10.addActionListener(controller);
		bet_50.addActionListener(controller);
		bet_100.addActionListener(controller);
		ddown.addActionListener(controller);	
	}
	
	public void enableDoubleDown(boolean enable) {
		ddown.setEnabled(enable);
	}
	
	public void enableBettingControls(boolean enable) {
		bet_10.setEnabled(enable);
		bet_50.setEnabled(enable);
		bet_100.setEnabled(enable);
		
	}
		public void enablePlayerControls (boolean enable) {
			hit.setEnabled(enable);
			stand.setEnabled(enable);
		}
		public void enableGameControls(boolean enable) {
			deal.setEnabled(enable);
			quit.setEnabled(enable);
			
		}
		protected OptionViewController makeController() {
			return new OptionViewController(player, dealer, this);
		}
		private void buildGUI() {
			JPanel betting_controls = new JPanel();
			JPanel game_controls = new JPanel();
			add (betting_controls, BorderLayout.NORTH);
			add(game_controls, BorderLayout.SOUTH);
			betting_controls.setBackground(FOREST_GREEN);
			game_controls.setBackground(FOREST_GREEN);
			ddown.setActionCommand(DOUBLE_DOWN);
			deal.setActionCommand(NEW_GAME);
			quit.setActionCommand(QUIT);
			hit.setActionCommand(HIT);
			stand.setActionCommand(STAND);
			bet_10.setActionCommand(BET_10);
			bet_50.setActionCommand(BET_50);
			bet_100.setActionCommand(BET_100);
			betting_controls.add(bet_10);
			betting_controls.add(bet_50);
			betting_controls.add(bet_100);
			game_controls.add(ddown);
			game_controls.add(hit);
			game_controls.add(stand);
			game_controls.add(deal);
			game_controls.add(quit);
			enableBettingControls(false);
			enablePlayerControls(false);
			enableDoubleDown(false);
			
			
			
			
			
			
			
		}
		
}
