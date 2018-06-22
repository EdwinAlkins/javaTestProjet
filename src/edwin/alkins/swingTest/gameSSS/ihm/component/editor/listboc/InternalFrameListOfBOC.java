package edwin.alkins.swingTest.gameSSS.ihm.component.editor.listboc;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import static edwin.alkins.swingTest.gameSSS.ihm.action.ActionRedefine.createStringActionCommand;

import java.awt.BorderLayout;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.ihm.action.ActionRedefine;
import edwin.alkins.swingTest.gameSSS.ihm.component.tree.BuilderMutableTreeNode;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InternalFrameListOfBOC extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8866621961529789019L;
	private JTree tree;
	private JButton btn_save;
	public final int id = ActionRedefine.getInstance().getIdAction();
	private JPanel panel;
	private Component horizontalGlue;
	private Component horizontalGlue_1;
	private Component horizontalGlue_2;
	private JButton btn_edit;
	private Component horizontalGlue_3;
	private JButton brn_delete;

	/**
	 * Create the frame.
	 */
	public InternalFrameListOfBOC(IBasicObjectCore data) {
		setBounds(100, 100, 450, 300);
		setTitle("Tree");
		
		DefaultTreeCellRenderer tCellRenderer = new  DefaultTreeCellRenderer();
		tCellRenderer.setClosedIcon(null);
		tCellRenderer.setOpenIcon(null);
		tCellRenderer.setLeafIcon(null);
		
		tree = new JTree();
		tree.setCellRenderer(tCellRenderer);
		tree.setModel(new DefaultTreeModel(new BuilderMutableTreeNode("root").addAutoBuildTree(data)));
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				if (tree.getLastSelectedPathComponent() != null) {
					DefaultMutableTreeNode obj = ((DefaultMutableTreeNode)tree.getLastSelectedPathComponent());
					SystemDataCore.getInstance().getLoggeurShell().printLog(((IBasicObjectCore)obj.getUserObject()).getRepresentation(), "black");
				}
			}
		});
		getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
	    
	    panel = new JPanel();
	    getContentPane().add(panel, BorderLayout.SOUTH);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    horizontalGlue = Box.createHorizontalGlue();
	    panel.add(horizontalGlue);
	    
	    btn_save = new JButton("Sauvgarder");
	    btn_save.setActionCommand(createStringActionCommand(this.getClass(),"save_boc",id));
	    btn_save.addActionListener(ActionRedefine.getInstance());
	    panel.add(btn_save);
	    
	    horizontalGlue_1 = Box.createHorizontalGlue();
	    panel.add(horizontalGlue_1);
	    
	    btn_edit = new JButton("Modifier");
	    btn_edit.setActionCommand(createStringActionCommand(this.getClass(),"edit_boc",id));
	    btn_edit.addActionListener(ActionRedefine.getInstance());
	    panel.add(btn_edit);
	    
	    horizontalGlue_2 = Box.createHorizontalGlue();
	    panel.add(horizontalGlue_2);
	    
	    brn_delete = new JButton("Supprimer");
	    brn_delete.setActionCommand(createStringActionCommand(this.getClass(),"delete_boc",id));
	    brn_delete.addActionListener(ActionRedefine.getInstance());
	    panel.add(brn_delete);
	    
	    horizontalGlue_3 = Box.createHorizontalGlue();
	    panel.add(horizontalGlue_3);
	    this.setVisible(true);
	}
	
	public JTree getTree() {
		return this.tree;
	}
}
