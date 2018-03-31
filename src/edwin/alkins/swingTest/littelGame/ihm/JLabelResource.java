package edwin.alkins.swingTest.littelGame.ihm;

import javax.swing.JLabel;

public class JLabelResource extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2540330649669811282L;
	private final String resource;
	
	public JLabelResource(String resource) {
		super();
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
	
	public String getText() {
		
		return this.resource+": "+super.getText();
	}
}
