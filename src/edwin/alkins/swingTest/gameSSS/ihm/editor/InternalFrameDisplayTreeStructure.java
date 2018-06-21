package edwin.alkins.swingTest.gameSSS.ihm.editor;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.ihm.action.ActionRedefine;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class InternalFrameDisplayTreeStructure extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8866621961529789019L;
	private JTree tree;
	private JButton btn_newStructure;
	private JButton btn_createBOC;

	/**
	 * Create the frame.
	 */
	public InternalFrameDisplayTreeStructure(IBasicObjectCore data) {
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
	    
	    JPanel panel = new JPanel();
	    getContentPane().add(panel, BorderLayout.SOUTH);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    Component horizontalGlue_2 = Box.createHorizontalGlue();
	    panel.add(horizontalGlue_2);
	    
	    btn_newStructure = new JButton("Ajouter une Structure");
	    btn_newStructure.setActionCommand(this.getClass().getName()+"add_strucutre");
	    panel.add(btn_newStructure);
	    btn_newStructure.addActionListener(ActionRedefine.getInstance());
	    
	    Component horizontalGlue = Box.createHorizontalGlue();
	    panel.add(horizontalGlue);
	    
	    btn_createBOC = new JButton("Cr\u00E9e un nouvelle object");
	    btn_createBOC.setActionCommand(this.getClass().getName()+"add_boc");
	    panel.add(btn_createBOC);
	    btn_createBOC.addActionListener(ActionRedefine.getInstance());
	    
	    Component horizontalGlue_1 = Box.createHorizontalGlue();
	    panel.add(horizontalGlue_1);
	    this.setVisible(true);
	}
	
	public JTree getTree() {
		return this.tree;
	}
}
