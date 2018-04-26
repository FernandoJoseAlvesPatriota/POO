package blackjack.ui.mvc;

import blackjack.core.*;
import javax.swing.*;
import java.awt.*;

public class CardView extends JLabel {
	
	private ImageIcon icon;
	
	public CardView (VCard card) {
		getImage(card.getImage());
		setIcon(icon);
		setBackground(Color.white);
		setOpaque(true);
	}
	
	private void getImage(String name) {
		java.net.URL url = this.getClass().getResource(name);
		icon = new ImageIcon (url);
	}
}
