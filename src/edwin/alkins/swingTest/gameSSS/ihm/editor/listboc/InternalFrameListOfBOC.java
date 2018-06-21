package edwin.alkins.swingTest.gameSSS.ihm.editor.listboc;

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
import edwin.alkins.swingTest.gameSSS.ihm.action.ActionRedefine;
import edwin.alkins.swingTest.gameSSS.ihm.component.tree.BuilderMutableTreeNode;

public class InternalFrameListOfBOC extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8866621961529789019L;
	private JTree tree;
	private JButton btn_modifier;
	public final int id = ActionRedefine.getInstance().getIdAction();

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
					System.out.println(((IBasicObjectCore)obj.getUserObject()).getRepresentation());
				}
			}
		});
		getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
		
		btn_modifier = new JButton("Sauvgarder");
		btn_modifier.setActionCommand(createStringActionCommand(this.getClass(),"save_boc",id));
		btn_modifier.addActionListener(ActionRedefine.getInstance());
	    this.getContentPane().add(btn_modifier, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
	
	public JTree getTree() {
		return this.tree;
	}
}
