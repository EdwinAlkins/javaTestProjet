package edwin.alkins.swingTest.gameSSS.ihm.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.instrument.ClassDefinition;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.core.scripting.IScript;
import edwin.alkins.swingTest.gameSSS.core.stockage.IStockage;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;

public class CreateStructureBOC extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -514857570771442255L;
	private final JPanel contentPanel = new JPanel();
	public interface ActionCreateBOC{
		public void create(IBasicObjectCore boc);
	}
	private ActionCreateBOC action = null;
	private JTextField jft_type;
	private Box verticalBox_container;
	public void setActionCreateBOC(ActionCreateBOC action) {
		this.action = action;
	}
	public ArrayList<JPanelSaisieStructure> listP = new ArrayList<>();
	private JComboBox<Object> jft_sous_type;

	/**
	 * Create the dialog.
	 */
	public CreateStructureBOC() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				verticalBox_container = Box.createVerticalBox();
				panel.add(verticalBox_container);
				{
					Box horizontalBox = Box.createHorizontalBox();
					verticalBox_container.add(horizontalBox);
					{
						JLabel label = new JLabel("Type : ");
						horizontalBox.add(label);
					}
					{
						jft_type = new JTextField();
						jft_type.setColumns(15);
						horizontalBox.add(jft_type);
					}
					
					{
						JLabel label = new JLabel("Sous_type : ");
						horizontalBox.add(label);
					}
					{
						jft_sous_type = new JComboBox<Object>(getListType().toArray());
						horizontalBox.add(jft_sous_type);
						horizontalBox.add(jft_sous_type);
					}
				}
			}
		}
		{
			JButton btn_add = new JButton("Ajouter element");
			btn_add.setActionCommand("Add");
			btn_add.addActionListener(this);
			contentPanel.add(btn_add, BorderLayout.SOUTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	private ArrayList<String> getListType() {
		ArrayList<String> listType = new ArrayList<>();
		listType.add(IBasicObjectCore.class.getName());
		listType.add(IScript.class.getName());
		listType.add(IStockage.class.getName());
		return listType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "OK":
				IBasicObjectCore boc = new BasicObjectCore(jft_type.getText());
				boc.setValue("type", jft_type.getText());
				boc.setValue("sous_type", jft_sous_type.getSelectedItem().toString());
				for(JPanelSaisieStructure panel:listP) {
					BasicObjectCore tmp = new BasicObjectCore(panel.getValue(JPanelSaisieStructure.ComponentOfPanel.name));
					tmp.setValue(JPanelSaisieStructure.ComponentOfPanel.name.name(),panel.getValue(JPanelSaisieStructure.ComponentOfPanel.name));
					tmp.setValue(JPanelSaisieStructure.ComponentOfPanel.type.name(),panel.getValue(JPanelSaisieStructure.ComponentOfPanel.type));
					tmp.setValue(JPanelSaisieStructure.ComponentOfPanel.sous_type.name(),panel.getValue(JPanelSaisieStructure.ComponentOfPanel.sous_type));
					tmp.setValue(JPanelSaisieStructure.ComponentOfPanel.defaut.name(),panel.getValue(JPanelSaisieStructure.ComponentOfPanel.defaut));
					boc.setValue(panel.getValue(JPanelSaisieStructure.ComponentOfPanel.name), tmp);
				}
				if(action!=null)
					action.create(boc);
				this.dispose();
			break;
		case "Add":
			Box horizontalBox = Box.createHorizontalBox();
			JPanelSaisieStructure jPanelSaisie = new JPanelSaisieStructure(this);
			listP.add(jPanelSaisie);
			horizontalBox.add(jPanelSaisie);
			verticalBox_container.add(horizontalBox);
			this.revalidate();
			this.pack();
			break;
		default:
			this.dispose();
			break;
		}
	}
}
