package edwin.alkins.swingTest.gameSSS.ihm.component.panel;

import javax.swing.JPanel;

public abstract class AbstactEditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64728734859418504L;

	/**
	 * Create the panel.
	 */
	public AbstactEditorPanel() {

	}

	public abstract Object getValue(String key);
}
