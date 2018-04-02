package edwin.alkins.swingTest.littelGame.ihm;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelByProduction extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelByProduction() {
		setLayout(new BorderLayout(0, 0));
		
		Box verticalBox_1 = Box.createVerticalBox();
		add(verticalBox_1, BorderLayout.CENTER);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_1);
		
		JButton btnUpgradeMineraux = new JButton("Mineraux");
		horizontalBox_1.add(btnUpgradeMineraux);
		
		JButton btnMinerauxDown = new JButton("<<");
		horizontalBox_1.add(btnMinerauxDown);
		
		JLabel lblMinerauxAdd = new JLabel("0");
		horizontalBox_1.add(lblMinerauxAdd);
		
		JButton btnMinerauxUp = new JButton(">>");
		horizontalBox_1.add(btnMinerauxUp);
		
		JLabel lblMinerauxPrice = new JLabel("0");
		horizontalBox_1.add(lblMinerauxPrice);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_2);
		
		JButton btnUpgradeEnergie = new JButton("Energie");
		horizontalBox_2.add(btnUpgradeEnergie);
		
		JButton btnEnergieDown = new JButton("<<");
		horizontalBox_2.add(btnEnergieDown);
		
		JLabel lblEnergieAdd = new JLabel("0");
		horizontalBox_2.add(lblEnergieAdd);
		
		JButton btnEnergieUp = new JButton(">>");
		horizontalBox_2.add(btnEnergieUp);
		
		JLabel lblNewLabel_5 = new JLabel("0");
		horizontalBox_2.add(lblNewLabel_5);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue_2);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_3);
		
		JButton btnUpgradePopulation = new JButton("Population");
		horizontalBox_3.add(btnUpgradePopulation);
		
		JButton btnPopulationDown = new JButton("<<");
		horizontalBox_3.add(btnPopulationDown);
		
		JLabel lblPopulationAdd = new JLabel("0");
		horizontalBox_3.add(lblPopulationAdd);
		
		JButton btnPopulationUp = new JButton(">>");
		horizontalBox_3.add(btnPopulationUp);
		
		JLabel lblPopulationPrice = new JLabel("0");
		horizontalBox_3.add(lblPopulationPrice);
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue_4);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_4);
		
		JButton btnUpgradeNourriture = new JButton("Nourriture");
		horizontalBox_4.add(btnUpgradeNourriture);
		
		JButton btnNourritureDown = new JButton("<<");
		horizontalBox_4.add(btnNourritureDown);
		
		JLabel lblNourritureAdd = new JLabel("0");
		horizontalBox_4.add(lblNourritureAdd);
		
		JButton btnNourritureUp = new JButton(">>");
		horizontalBox_4.add(btnNourritureUp);
		
		JLabel lblNourriturePrice = new JLabel("0");
		horizontalBox_4.add(lblNourriturePrice);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		verticalBox_1.add(verticalGlue_3);
	}

}
