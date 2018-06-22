package edwin.alkins.swingTest.gameSSS.ihm.component.editor.listboc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.core.scripting.IScript;
import edwin.alkins.swingTest.gameSSS.core.stockage.IStockage;
import edwin.alkins.swingTest.gameSSS.ihm.component.dialog.AbstactEditorDialog;
import edwin.alkins.swingTest.gameSSS.ihm.component.panel.AbstactEditorPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PanelEditBoc extends AbstactEditorPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6442878236108601183L;
	
	private Map<String,JComponent> mapObj = new HashMap<String,JComponent>();
	private ArrayList<JComponent> listOfAttribut = new ArrayList<>();

	/**
	 * Create the panel.
	 * @param createModelBOC 
	 */
	public PanelEditBoc(String type, AbstactEditorDialog createModelBOC) {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
		);
		groupLayout.setAutoCreateGaps(true);
		setLayout(groupLayout);
		
		GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        GroupLayout.Group yLabelGroup = groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        hGroup.addGroup(yLabelGroup);
        GroupLayout.Group yFieldGroup = groupLayout.createParallelGroup();
        hGroup.addGroup(yFieldGroup);
        groupLayout.setHorizontalGroup(hGroup);
        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        groupLayout.setVerticalGroup(vGroup);

        int p = GroupLayout.PREFERRED_SIZE;
		
		
		IBasicObjectCore structureBoc = (IBasicObjectCore) SystemDataCore.getInstance().getStructuredBOC().getValue(type);
		for(String head:structureBoc.getHeader()) {
			if(structureBoc.getValue(head) instanceof IBasicObjectCore) {
				IBasicObjectCore value = (IBasicObjectCore) structureBoc.getValue(head);
				
				JLabel lbl_edite = new JLabel(head+" : ");
				yLabelGroup.addComponent(lbl_edite);
				
				JComponent jtf_edite = null;
				if(getListType().contains(value.getValue("type")) && value.getValue("sous_type").equals(Object.class.getName())) {
					jtf_edite = new JTextField();
					((JTextField)jtf_edite).setColumns(10);
				}
				else if(value.getValue("type").equals(ArrayList.class.getName())) {
					jtf_edite = new JLabel("réféchire pour la liste");
				}
				else if(value.getValue("sous_type").equals(File.class.getName())) {
					jtf_edite = new JButton(System.getProperty("user.dir"));
					JButton btn_tmp = ((JButton)jtf_edite);
					btn_tmp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
							jfc.showOpenDialog(null);
							File currentFile = jfc.getSelectedFile();
							btn_tmp.setText(currentFile.getAbsolutePath());
							createModelBOC.pack();
						}
					});
				}
				else if(SystemDataCore.getInstance().getBOC().getValue("listOfType") instanceof ArrayList){
					IBasicObjectCore boc = SystemDataCore.getInstance().getBOC();
					ArrayList<IBasicObjectCore> listOfType = (ArrayList<IBasicObjectCore>) boc.getValue("listOfType");
					ArrayList<String> listToJcb = new ArrayList<>();
					for(IBasicObjectCore currentType:listOfType) {
						if(currentType.getType().equals(value.getValue("sous_type")) && currentType.getValue("listOfElements") instanceof ArrayList<?>) {
							for(IBasicObjectCore el: (ArrayList<IBasicObjectCore>)currentType.getValue("listOfElements")) {
								listToJcb.add(el.getName());
							}
						}
					}
					jtf_edite = new JComboBox<Object>(listToJcb.toArray());
				}
				else
					jtf_edite = new JLabel("ERROR");
				jtf_edite.setName(head);
				mapObj.put(head, jtf_edite);
				listOfAttribut.add(jtf_edite);
				yFieldGroup.addComponent(jtf_edite, p, p, p);
				
				vGroup.addGroup(groupLayout.createParallelGroup().
	                    addComponent(lbl_edite).
	                    addComponent(jtf_edite, p, p, p));
			}
		}
	}

	private ArrayList<String> getListType() {
		ArrayList<String> listType = new ArrayList<>();
		listType.add(Integer.class.getName());
		listType.add(Float.class.getName());
		listType.add(Double.class.getName());
		listType.add(String.class.getName());
		listType.add(Long.class.getName());
		return listType;
	}
	
	public ArrayList<JComponent> getListOfElement(){
		return listOfAttribut;
	}
	
	@Override
	public Object getValue(String key) {
		Object str = new Object();
		if(mapObj.containsKey(key)) {
			JComponent comp = mapObj.get(key);
			if(comp instanceof JTextField)
				str = ((JTextField) comp).getText();
			else if(comp instanceof JComboBox<?>)
				str = ((JComboBox<?>) comp).getSelectedItem().toString();
			else if(comp instanceof JButton)
				str = ((JButton) comp).getText();
		}
		return str;
	}

	public void setData(IBasicObjectCore boc) {
		for(String head:boc.getHeader()) {
			if(mapObj.containsKey(head)) {
				JComponent comp = mapObj.get(head);
				if(comp instanceof JTextField)
					((JTextField) comp).setText(boc.getValue(head).toString());
				else if(comp instanceof JComboBox<?>)
					((JComboBox<?>) comp).setSelectedItem(boc.getValue(head).toString());
				else if(comp instanceof JButton)
					((JButton) comp).setText(boc.getValue(head).toString());
			}
		}
	}

}
