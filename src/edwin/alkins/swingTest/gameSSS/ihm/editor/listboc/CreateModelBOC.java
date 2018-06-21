package edwin.alkins.swingTest.gameSSS.ihm.editor.listboc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.ihm.component.dialog.AbstactEditorDialog;
import edwin.alkins.swingTest.gameSSS.ihm.editor.structure.PanelEditStructure;
import javax.swing.JLabel;
import javax.swing.Box;

public class CreateModelBOC extends AbstactEditorDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -514857570771442255L;
	private JComboBox<Object> jcb_type;
	private Box verticalBox_container;
	protected ArrayList<PanelEditBoc> listP = new ArrayList<>();

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
				for(PanelEditBoc panel:listP) {
					for(JComponent comp:panel.getListOfElement()) {
						boc.setValue(comp.getName(), panel.getValue(comp.getName()));
						if(comp.getName().equals("name"))
							boc.setName(panel.getValue(comp.getName()).toString());
					}
				}
				ArrayList<IBasicObjectCore> structureBoc = (ArrayList<IBasicObjectCore>) SystemDataCore.getInstance().getBOC().getValue("listOfType");
				for(IBasicObjectCore currentType:structureBoc) {
					if(currentType.getType().equals(jcb_type.getSelectedItem().toString())) {
						((ArrayList<IBasicObjectCore>)currentType.getValue("listOfElements")).add(boc);
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
}
