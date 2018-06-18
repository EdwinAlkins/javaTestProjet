package edwin.alkins.swingTest.gameSSS.ihm.editor;

import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.util.ArrayList;
import java.util.HashMap;

public class JPanelSaisie extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8910744911830804465L;
	private JTextField name;

	private HashMap<String,JComponent> mapObj = new HashMap<>();
	private JTextField type_BOC;
	private Box infoBOC;
	public enum ComponentOfPanel{
		element,
		type,
		typeboc;
	}
	
	/**
	 * Create the panel.
	 */
	public JPanelSaisie(ArrayList<String> list, JDialog father) {
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
		comboBox_listEl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBox_listEl.getSelectedItem().toString().equals(ArrayList.class.getName()) ||
						comboBox_listEl.getSelectedItem().toString().equals(IBasicObjectCore.class.getName()))
					setVisibleBoc(true);
				else
					setVisibleBoc(false);
				father.revalidate();
				father.pack();
			}
		});
		mapObj.put(ComponentOfPanel.type.name(), comboBox_listEl);
		
		infoBOC = Box.createHorizontalBox();
		horizontalBox.add(infoBOC);
		infoBOC.setVisible(false);
		
		JLabel lbl_typeBOC = new JLabel("type du BOC : ");
		infoBOC.add(lbl_typeBOC);
		
		type_BOC = new JTextField();
		infoBOC.add(type_BOC);
		type_BOC.setColumns(10);
		mapObj.put(ComponentOfPanel.typeboc.name(), type_BOC);
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
	
	public void setVisibleBoc(boolean b) {
		infoBOC.setVisible(b);
	}
}
