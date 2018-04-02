package edwin.alkins.swingTest.littelGame.ihm;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class JPanelPlanet extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelPlanet() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_3);
		
		JButton btnProduction = new JButton("Production");
		horizontalBox.add(btnProduction);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnNewButton = new JButton("Flotte");
		horizontalBox.add(btnNewButton);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);
		
		JButton btnNewButton_1 = new JButton("Recherche");
		horizontalBox.add(btnNewButton_1);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_2);
		
		JPanel panel = new JPanelByProduction();
		verticalBox.add(panel);
	}

}
