package edwin.alkins.swingTest.littelGame2.ihm.launcher.vue.inventory;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class InternalFrameInventory extends JInternalFrame {
	private static final long serialVersionUID = 8921196199831468862L;

	 private static final int SIDE = 5;

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
		
		JScrollPane scrollPaneItemView = new JScrollPane();
		splitPane.setRightComponent(scrollPaneItemView);
		
		JPanel panel = new JPanel();
		scrollPaneItemView.setViewportView(panel);
		GridLayout gl_panel = new GridLayout(SIDE, SIDE);
		gl_panel.setHgap(1);
		panel.setLayout(gl_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{splitPane, scrollPaneListInventory, scrollPaneItemView}));

	}
}
