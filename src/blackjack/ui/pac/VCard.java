package blackjack.ui.pac;

import blackjack.core.*;
import javax.swing.*;
import java.awt.*;


public class VCard  extends Card implements Displayable {
	
	private String image;
	private CardView view;
	
	public VCard (Suit suit, Rank rank, String image) {
		super(suit,rank);
		this.image = image;
		view = new CardView (getImage());
	}
	
	public void setFaceUp (boolean up) {
		super.setFaceUp(up);
		view.changed();
	}
	
	public JComponent view() {
		return view;
	}
	
	private String getImage() {
		if(isFaceUp()) {
			return image;
		} else {
			return "/blackjack/ui/bitmaps/empty_pile.xbm";
		}
	}

		private class CardView extends JLabel {
			
			public CardView(String image) {
				setImage(image);
				setBackground (Color.white);
				setOpaque(true);
			}
			
			public void changed () {
				setImage(getImage());
			}
			
			private void setImage (String image) {
				java.net.URL url = this.getClass().getResource(image);
				String ulr = null;
				ImageIcon icon = new ImageIcon (ulr);
				setIcon(icon);
			}
			
}
}