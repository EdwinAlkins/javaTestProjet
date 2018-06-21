package edwin.alkins.swingTest.gameSSS.ihm.editor;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.core.scripting.IScript;
import edwin.alkins.swingTest.gameSSS.core.stockage.IStockage;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.util.ArrayList;
import java.util.EnumMap;
import javax.swing.BoxLayout;
import java.awt.Component;

public class JPanelSaisieStructure extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8910744911830804465L;
	private JTextField jtf_name;

	private EnumMap<ComponentOfPanel,JComponent> mapObj = new EnumMap<ComponentOfPanel,JComponent>(ComponentOfPanel.class);
	public enum ComponentOfPanel{
		name,
		type,
		sous_type,
		defaut;
	}
	
	/**
	 * Create the panel.
	 */
	public JPanelSaisieStructure(JDialog father) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		
		JLabel lbl_nomEl = new JLabel("Nom de l'element : ");
		horizontalBox.add(lbl_nomEl);
		
		jtf_name = new JTextField();
		horizontalBox.add(jtf_name);
		jtf_name.setColumns(10);
		mapObj.put(ComponentOfPanel.name, jtf_name);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		JLabel typeElement = new JLabel("Type de l'element : ");
		horizontalBox.add(typeElement);
		
		JComboBox<Object> jcb_type = new JComboBox<Object>(getListType().toArray());
		horizontalBox.add(jcb_type);
		add(horizontalBox);
		mapObj.put(ComponentOfPanel.type, jcb_type);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);
		
		JLabel lbl_sous_type = new JLabel("Sous type : ");
		horizontalBox.add(lbl_sous_type);
		
		JComboBox<Object> jcb_sous_type = new JComboBox<Object>(getListSousType().toArray());
		horizontalBox.add(jcb_sous_type);
		mapObj.put(ComponentOfPanel.sous_type, jcb_sous_type);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);
		
		JLabel lbl_defaut = new JLabel("Valeur par Defaut : ");
		horizontalBox.add(lbl_defaut);
		
		JTextField jtf_defaut = new JTextField();
		jtf_defaut.setColumns(10);
		horizontalBox.add(jtf_defaut);
		mapObj.put(ComponentOfPanel.defaut, jtf_defaut);
	}
	
	private ArrayList<String> getListType() {
		ArrayList<String> listType = new ArrayList<>();
		listType.add(Integer.class.getName());
		listType.add(Float.class.getName());
		listType.add(Double.class.getName());
		listType.add(String.class.getName());
		listType.add(Long.class.getName());
		listType.add(ArrayList.class.getName());
		listType.add(IBasicObjectCore.class.getName());
		listType.add(IScript.class.getName());
		listType.add(IStockage.class.getName());
		return listType;
	}
	
	private ArrayList<String> getListSousType() {
		ArrayList<String> listType = new ArrayList<>();
		listType.add(Object.class.getName());
		for(String head:SystemDataCore.getInstance().getStructuredBOC().getHeader()) {
			listType.add(head);
		}
		return listType;
	}
	
	public String getValue(ComponentOfPanel key) {
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
