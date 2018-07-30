package edwin.alkins.swingTest.littelGame2.ihm.launcher.vue.inventory;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.Box;

public class InternalFrameInventory extends JInternalFrame {
	/**
	 * @wbp.nonvisual location=61,469
	 */
	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalFrameInventory frame = new InternalFrameInventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InternalFrameInventory() {
		setClosable(true);
		setResizable(true);
		setBounds(100, 100, 720, 528);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPaneListInventory = new JScrollPane();
		splitPane.setLeftComponent(scrollPaneListInventory);
		
		JPanel panel = new JPanel();
		scrollPaneListInventory.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_4);
		
		JButton button = new JButton("");
		horizontalBox.add(button);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton button_1 = new JButton("");
		horizontalBox.add(button_1);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);
		
		JButton button_2 = new JButton("");
		horizontalBox.add(button_2);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_2);
		
		JButton button_3 = new JButton("");
		horizontalBox.add(button_3);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_3);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalBox.add(verticalGlue_1);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_5);
		
		JButton button_4 = new JButton("");
		horizontalBox_1.add(button_4);
		
		Component horizontalGlue_6 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_6);
		
		JButton button_5 = new JButton("");
		horizontalBox_1.add(button_5);
		
		Component horizontalGlue_7 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_7);
		
		JButton button_6 = new JButton("");
		horizontalBox_1.add(button_6);
		
		Component horizontalGlue_8 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_8);
		
		JButton button_7 = new JButton("");
		horizontalBox_1.add(button_7);
		
		Component horizontalGlue_9 = Box.createHorizontalGlue();
		horizontalBox_1.add(horizontalGlue_9);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		verticalBox.add(verticalGlue_2);
		
		JScrollPane scrollPaneItemView = new JScrollPane();
		splitPane.setRightComponent(scrollPaneItemView);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{splitPane, scrollPaneListInventory, scrollPaneItemView}));

	}
}
