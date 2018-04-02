package edwin.alkins.swingTest.littelGame.ihm;

import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame.core.ManagerIHM;
import edwin.alkins.swingTest.littelGame.core.ManagerProduction;
import edwin.alkins.swingTest.littelGame.core.SetupParameters;
import edwin.alkins.swingTest.littelGame.exception.ExceptionCantAddJComponentInFrame;
import edwin.alkins.swingTest.littelGame.exception.ExceptionComponentIsNotInTheContainer;

import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelResourceDisplay extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8709178043525895920L;
	private boolean t;

	/**
	 * Create the panel.
	 */
	public JPanelResourceDisplay() {
		setLayout(new BorderLayout(0, 0));
		
		Box globalVerticalBox = Box.createVerticalBox();
		add(globalVerticalBox);
		
		Box horizontalBox = Box.createHorizontalBox();
		globalVerticalBox.add(horizontalBox);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton homeButton = new JButton("Home button");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerIHM manager = ManagerIHM.getInstance();
				try {
					manager.replaceComponentOfTheIHM(BorderLayout.NORTH, JPanelLoading.class.getName(), BorderLayout.NORTH);
				} catch (ExceptionComponentIsNotInTheContainer e1) {
					e1.printStackTrace();
				} catch (ExceptionCantAddJComponentInFrame e1) {
					e1.printStackTrace();
				}
			}
		});
		horizontalBox.add(homeButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);
		
		JLabel resourceMetal = new JLabelResource(ManagerProduction.MINERAUX);
		horizontalBox.add(resourceMetal);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_2);
		
		JLabel resourceCristaux = new JLabelResource(ManagerProduction.NOURRITURE);
		horizontalBox.add(resourceCristaux);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_3);
		
		JLabel resourceDeuterium = new JLabelResource(ManagerProduction.POPULATION);
		horizontalBox.add(resourceDeuterium);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_4);
		
		JLabel resourceEnergie = new JLabelResource(ManagerProduction.ENERGIE);
		horizontalBox.add(resourceEnergie);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_5);
		
		JButton btnNewButton = new JButton("New button");
		horizontalBox.add(btnNewButton);
		
		Component glue = Box.createGlue();
		globalVerticalBox.add(glue);
		SetupParameters s = new SetupParameters();
		ManagerProduction p = s.getProduction();
		p.addHandleurOnProduction(new ManagerProduction.ProducesHandler() {
			@Override
			public void resource(String name, double quantity) {
				if(name.equals(ManagerProduction.MINERAUX))
					resourceMetal.setText(Math.round(quantity)+"");
				if(name.equals(ManagerProduction.NOURRITURE))
					resourceCristaux.setText(Math.round(quantity)+"");
				if(name.equals(ManagerProduction.POPULATION))
					resourceDeuterium.setText(Math.round(quantity)+"");
				if(name.equals(ManagerProduction.ENERGIE))
					resourceEnergie.setText(Math.round(quantity)+"");
			}
		});
		t = true;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(t) p.stopProduction();
				else p.startProduction();
				t = !t;
			}
		});
	}

}
