package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.TreeUI;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;

public class InternalFrameV1 extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8866621961529789019L;

	/**
	 * Create the frame.
	 */
	public InternalFrameV1() {
		setBounds(100, 100, 450, 300);
		setTitle("Tree");
		
		DefaultTreeCellRenderer tCellRenderer = new  DefaultTreeCellRenderer();
		tCellRenderer.setClosedIcon(null);
		tCellRenderer.setOpenIcon(null);
		tCellRenderer.setLeafIcon(null);
		
		JTree tree = new JTree();
		tree.setCellRenderer(tCellRenderer);
		tree.setModel(new DefaultTreeModel(new BuilderMutableTreeNode("root").addAutoBuildTree(instance())));
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
	}

	private IBasicObjectCore instance() {
		IBasicObjectCore arme0 = new BasicObjectCore("arme");
		arme0.setValue("name", new String("blasteur"));
		arme0.setValue("atk", new Integer(10));
		arme0.setValue("vitesse", new Float(20f));

		IBasicObjectCore arme1 = new BasicObjectCore("arme");
		arme1.setValue("name", new String("canion"));
		arme1.setValue("atk", new Integer(100));
		arme1.setValue("vitesse", new Float(10f));
		
		List<IBasicObjectCore> listArme0 = new ArrayList<>();
		listArme0.add(arme0);
		List<IBasicObjectCore> listArme1 = new ArrayList<>();
		listArme1.add(arme1);

		IBasicObjectCore vaiseau0 = new BasicObjectCore("vaiseau");
		vaiseau0.setValue("name", new String("torpido"));
		vaiseau0.setValue("size_stockage", new Integer(1000));
		vaiseau0.setValue("list_arme", listArme0);

		IBasicObjectCore vaiseau1 = new BasicObjectCore("vaiseau");
		vaiseau1.setValue("name", new String("vitrio"));
		vaiseau1.setValue("size_stockage", new Integer(100));
		vaiseau1.setValue("list_arme", listArme1);

		List<IBasicObjectCore> listVaiseau = new ArrayList<>();
		listVaiseau.add(vaiseau0);
		listVaiseau.add(vaiseau1);
		IBasicObjectCore secteur0 = new BasicObjectCore("secteur");
		secteur0.setValue("id", new Integer(0));
		secteur0.setValue("name", new String("secteur de naissance"));
		secteur0.setValue("list_vaiseaux", listVaiseau);
		
		List<IBasicObjectCore> listSecteur0 = new ArrayList<>();
		listSecteur0.add(secteur0);
		IBasicObjectCore galaxy0 = new BasicObjectCore("galaxy");
		galaxy0.setValue("id", new Integer(0));
		galaxy0.setValue("name", new String("galaxy ambrion"));
		galaxy0.setValue("list_secteur", listSecteur0);
		
		IBasicObjectCore BOC_descriptor = new BasicObjectCore("type");
		BOC_descriptor.setValue("name", new String("BOC descriptor"));
		
		IBasicObjectCore core = new BasicObjectCore("core");
		core.setValue("name", new String("engine"));
		core.setValue("world", galaxy0);
		core.setValue("descriptor", BOC_descriptor);
		return core;
	}
}
