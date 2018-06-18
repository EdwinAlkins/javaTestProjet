package edwin.alkins.swingTest.gameSSS.ihm.editor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.ihm.editor.CreateStructureBOC.ActionCreateBOC;

public class InternalFrameV1 extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8866621961529789019L;
	private JTree tree;
	private JButton bouton;

	/**
	 * Create the frame.
	 */
	public InternalFrameV1(IBasicObjectCore data) {
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
					System.out.println(((IBasicObjectCore)obj.getUserObject()).getRepresentation());
				}
			}
		});
		getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
		
		bouton = new JButton("Ajouter");
	    this.getContentPane().add(bouton, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
	
	public void setActionButton(ActionListener action) {
		bouton.addActionListener(action);
	}
	
	public JTree getTree() {
		return this.tree;
	}
}
