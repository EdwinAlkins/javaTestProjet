package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;

public class CreateBOC extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -514857570771442255L;
	private final JPanel contentPanel = new JPanel();
	public interface ActionCreateBOC{
		public void create(IBasicObjectCore boc);
	}
	private ActionCreateBOC action = null;
	private JTextField typeOfBOC;
	private Box verticalBox_container;
	public void setActionCreateBOC(ActionCreateBOC action) {
		this.action = action;
	}
	public ArrayList<JPanelSaisie> listP = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateBOC dialog = new CreateBOC();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateBOC() {
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
						JLabel label = new JLabel("type : ");
						horizontalBox.add(label);
					}
					{
						typeOfBOC = new JTextField();
						typeOfBOC.setColumns(15);
						horizontalBox.add(typeOfBOC);
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
		IBasicObjectCore structure = SystemDataCore.getInstance().getStructuredBOC();
		for(String head:structure.getHeader())
			listType.add(head);
		return listType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "OK":
				IBasicObjectCore boc = new BasicObjectCore(typeOfBOC.getText());
				boc.setName(typeOfBOC.getText());
				for(JPanelSaisie panel:listP) {
					boc.setValue(panel.getValue(JPanelSaisie.ComponentOfPanel.element.name()),panel.getValue(JPanelSaisie.ComponentOfPanel.type.name()));
				}
				if(action!=null)
					action.create(boc);
				this.dispose();
			break;
		case "Add":
			Box horizontalBox = Box.createHorizontalBox();
			JPanelSaisie jPanelSaisie = new JPanelSaisie(getListType(), this);
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
