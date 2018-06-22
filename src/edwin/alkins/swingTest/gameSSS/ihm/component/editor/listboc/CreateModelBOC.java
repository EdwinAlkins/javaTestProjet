package edwin.alkins.swingTest.gameSSS.ihm.component.editor.listboc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.ihm.component.dialog.AbstactEditorDialog;
import edwin.alkins.swingTest.gameSSS.ihm.component.editor.structure.PanelEditStructure;
import edwin.alkins.swingTest.gameSSS.ihm.component.tree.BuilderMutableTreeNode;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTree;

import java.awt.Component;

public class CreateModelBOC extends AbstactEditorDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -514857570771442255L;
	private JComboBox<Object> jcb_type;
	private Box verticalBox_container;
	protected ArrayList<PanelEditBoc> listP = new ArrayList<>();
	private JTextField jft_designation;

	/**
	 * Create the dialog.
	 */
	public CreateModelBOC() {
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
						JLabel lbl_type = new JLabel("type : ");
						horizontalBox.add(lbl_type);
					}
					{
						jcb_type = new JComboBox<Object>(getListType().toArray());
						horizontalBox.add(jcb_type);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						horizontalBox.add(horizontalStrut);
					}
					{
						JLabel lbl_designation = new JLabel("designation");
						horizontalBox.add(lbl_designation);
					}
					{
						jft_designation = new JTextField();
						horizontalBox.add(jft_designation);
						jft_designation.setColumns(10);
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
	
	public void setData(IBasicObjectCore boc) {
		jcb_type.setSelectedItem(boc.getType());
		jft_designation.setText(boc.getName());
		Box horizontalBox = Box.createHorizontalBox();
		jcb_type.setEnabled(false);
		PanelEditBoc jPanelSaisie = new PanelEditBoc(jcb_type.getSelectedItem().toString(), this);
		jPanelSaisie.setData(boc);
		listP.add(jPanelSaisie);
		horizontalBox.add(jPanelSaisie);
		verticalBox_container.add(horizontalBox);
		this.revalidate();
		this.pack();
	}

	protected ArrayList<String> getListType() {
		ArrayList<String> listType = new ArrayList<>();
		IBasicObjectCore structure = SystemDataCore.getInstance().getStructuredBOC();
		for(String head:structure.getHeader())
			listType.add(head);
		return listType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "OK":
				IBasicObjectCore boc = new BasicObjectCore(jcb_type.getSelectedItem().toString());
				boc.setName(jft_designation.getText());
				for(PanelEditBoc panel:listP) {
					for(JComponent comp:panel.getListOfElement()) {
						boc.setValue(comp.getName(), panel.getValue(comp.getName()));
					}
				}
				if(action!=null)
					action.create(boc);
				this.dispose();
			break;
		case "Add":
			Box horizontalBox = Box.createHorizontalBox();
			jcb_type.setEnabled(false);
			PanelEditBoc jPanelSaisie = new PanelEditBoc(jcb_type.getSelectedItem().toString(), this);
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

	@Override
	public void update(JTree tree) {
		tree.setModel(
				new DefaultTreeModel(new BuilderMutableTreeNode("root").addAutoBuildTree(SystemDataCore.getInstance().getBOC())));
		tree.revalidate();
	}
}
