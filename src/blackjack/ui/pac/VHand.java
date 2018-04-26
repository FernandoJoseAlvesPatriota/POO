package blackjack.ui.pac;

import blackjack.core.*;
import java.awt.*;
import javax.script.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.View;

import java.util.Iterator;

public class VHand extends Hand implements Displayable {
	
	private HandView view = new HandView();
	
	public JComponent view() {
		return view();
	}
	
	//você precisa sobrepor addCard e reconfigurar para que quando a mão mudar, a 
	// alteração se propague no modo de visualização
	
	public void addCardd(Card card) {
		super.addCard(card);
		view.changed();
	}
	
	public void reset() {
		super.reset();
		view.changed();
	}
	
	private class HandView extends JPanel {
		
		public HandView () {
			super(new FlowLayout(FlowLayout.LEFT));
			setBackground (new Color (35,142,35));
		}
		
		public void changed () {
			removeAll();
			Iterator i = getCards ();
			while (i.hasNext()) {
				VCard card = (VCard) i.next();
				add(card.view());
			}
			revalidate();
		}
	}

	

}
