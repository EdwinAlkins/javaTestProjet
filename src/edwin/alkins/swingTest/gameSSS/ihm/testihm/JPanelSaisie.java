package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class JPanelSaisie extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8910744911830804465L;
	private JTextField name;

	private HashMap<String,JComponent> mapObj = new HashMap<>();
	public enum ComponentOfPanel{
		element,
		type;
	}
	
	/**
	 * Create the panel.
	 */
	public JPanelSaisie(ArrayList<String> list) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box horizontalBox = Box.createHorizontalBox();
		
		JLabel lbl_nomEl = new JLabel("nom de l'element : ");
		horizontalBox.add(lbl_nomEl);
		
		name = new JTextField();
		horizontalBox.add(name);
		name.setColumns(10);
		mapObj.put(ComponentOfPanel.element.name(), name);
		
		JLabel typeElement = new JLabel("type de l'element : ");
		horizontalBox.add(typeElement);
		
		JComboBox<Object> comboBox_listEl = new JComboBox<Object>(list.toArray());
		horizontalBox.add(comboBox_listEl);
		add(horizontalBox);
		mapObj.put(ComponentOfPanel.type.name(), comboBox_listEl);
	}
	
	public String getValue(String key) {
		String str = new String();
		if(mapObj.containsKey(key)) {
			JComponent comp = mapObj.get(key);
			if(comp instanceof JTextField)
				str = ((JTextField)comp).getText();
			else if(comp instanceof JComboBox<?>)
				str = ((JComboBox<?>)comp).getSelectedItem().toString();
		}
		return str;
	}
}
