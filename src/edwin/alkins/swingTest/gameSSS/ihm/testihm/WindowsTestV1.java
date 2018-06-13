package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.ihm.testihm.CreateBOC.ActionCreateBOC;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;

public class WindowsTestV1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowsTestV1 window = new WindowsTestV1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowsTestV1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		SystemDataCore.getInstance().setBOC(instance());

		InternalFrameV1 internalFrame = new InternalFrameV1(SystemDataCore.getInstance().getStructuredBOC());
		ActionListener action0 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JTree tree = internalFrame.getTree();
				if (tree.getLastSelectedPathComponent() != null) {
					CreateStructureBOC createdBoc = new CreateStructureBOC();
					createdBoc.setVisible(true);
					createdBoc.setActionCreateBOC(new CreateStructureBOC.ActionCreateBOC() {
						public void create(IBasicObjectCore boc) {
							DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree
									.getLastSelectedPathComponent();
							((IBasicObjectCore) parentNode.getUserObject()).setValue(boc.getType(), boc);
							tree.setModel(
									new DefaultTreeModel(new BuilderMutableTreeNode("root").addAutoBuildTree(SystemDataCore.getInstance().getStructuredBOC())));
							tree.revalidate();
						}
					});
				} else {
					System.out.println("Aucune sélection !");
				}
			}
		};
		internalFrame.setActionButton(action0);
		internalFrame.setResizable(true);
		internalFrame.setBounds(0, 0, 228, 261);
		layeredPane.add(internalFrame);
		internalFrame.setVisible(true);
		
		InternalFrameV1 internalFrame1 = new InternalFrameV1(SystemDataCore.getInstance().getBOC());
		ActionListener action1 = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JTree tree = internalFrame.getTree();
				if (tree.getLastSelectedPathComponent() != null) {
					CreateBOC createdBoc = new CreateBOC();
					createdBoc.setVisible(true);
					createdBoc.setActionCreateBOC(new CreateBOC.ActionCreateBOC() {
						public void create(IBasicObjectCore boc) {
							DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree
									.getLastSelectedPathComponent();
							((IBasicObjectCore) parentNode.getUserObject()).setValue(boc.getType(), boc);
							tree.setModel(
									new DefaultTreeModel(new BuilderMutableTreeNode("root").addAutoBuildTree(SystemDataCore.getInstance().getBOC())));
							tree.revalidate();
						}
					});
				} else {
					System.out.println("Aucune sélection !");
				}
			}
		};
		internalFrame1.setActionButton(action1);
		internalFrame1.setResizable(true);
		internalFrame1.setBounds(0, 0, 228, 261);
		layeredPane.add(internalFrame1);
		internalFrame1.setVisible(true);
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
		
		List<IBasicObjectCore> listGalaxy0 = new ArrayList<>();
		listGalaxy0.add(galaxy0);
		IBasicObjectCore core = new BasicObjectCore("core");
		core.setValue("name", new String("engine"));
		core.setValue("world", listGalaxy0);
		return core;
	}
}
