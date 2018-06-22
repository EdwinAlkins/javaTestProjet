package edwin.alkins.swingTest.gameSSS.ihm.component.editor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.ReaderJDOMboc;
import edwin.alkins.swingTest.gameSSS.core.basicObj.SystemDataCore;
import edwin.alkins.swingTest.gameSSS.ihm.action.ActionRedefine;
import edwin.alkins.swingTest.gameSSS.ihm.component.dialog.AbstactEditorDialog;
import edwin.alkins.swingTest.gameSSS.ihm.component.editor.listboc.CreateModelBOC;
import edwin.alkins.swingTest.gameSSS.ihm.component.editor.listboc.InternalFrameListOfBOC;
import edwin.alkins.swingTest.gameSSS.ihm.component.editor.structure.CreateStructureBOC;
import edwin.alkins.swingTest.gameSSS.ihm.component.editor.structure.InternalFrameDisplayTreeStructure;
import edwin.alkins.swingTest.gameSSS.ihm.component.logshell.InternalFrameLogShell;
import edwin.alkins.swingTest.gameSSS.ihm.component.tree.BuilderMutableTreeNode;

import static edwin.alkins.swingTest.gameSSS.ihm.action.ActionRedefine.createStringActionCommand;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Component;

public class WindowsEditorV1 {

	private JFrame frame;
	private InternalFrameDisplayTreeStructure internalFrameStructure;
	private InternalFrameListOfBOC internalFrameBOC;
	private InternalFrameLogShell internalFrameLog;

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
					WindowsEditorV1 window = new WindowsEditorV1();
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
	public WindowsEditorV1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		internalFrameStructure = new InternalFrameDisplayTreeStructure(SystemDataCore.getInstance().getStructuredBOC());
		AbstractAction actionAddStructure = new AbstractAction() {
			private static final long serialVersionUID = -7929774017395285040L;
			public void actionPerformed(ActionEvent event) {
				JTree tree = internalFrameStructure.getTree();
				if (tree.getLastSelectedPathComponent() != null) {
					AbstactEditorDialog createdBoc = new CreateStructureBOC();
					createdBoc.pack();
					createdBoc.setVisible(true);
					createdBoc.setActionCreateBOC(new AbstactEditorDialog.ActionCreateBOC() {
						public void create(IBasicObjectCore boc) {
							DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree
									.getLastSelectedPathComponent();
							((IBasicObjectCore) parentNode.getUserObject()).setValue(boc.getType(), boc);
							createdBoc.update(internalFrameStructure.getTree());
						}
					});
				} else {
					SystemDataCore.getInstance().getLoggeurShell().printLog("Aucune sélection !", "red");
				}
			}
		};
		ActionRedefine.getInstance().setAction(createStringActionCommand(internalFrameStructure.getClass(),"add_strucutre",internalFrameStructure.id), actionAddStructure);
		AbstractAction actionAddBOC = new AbstractAction() {
			private static final long serialVersionUID = 272006760717120336L;
			public void actionPerformed(ActionEvent event) {
					AbstactEditorDialog createdBoc = new CreateModelBOC();
					createdBoc.pack();
					createdBoc.setVisible(true);
					createdBoc.setActionCreateBOC(new AbstactEditorDialog.ActionCreateBOC() {
						public void create(IBasicObjectCore boc) {
							ArrayList<IBasicObjectCore> structureBoc = (ArrayList<IBasicObjectCore>) SystemDataCore.getInstance().getBOC().getValue("listOfType");
							for(IBasicObjectCore currentType:structureBoc) {
								if(currentType.getType().equals(boc.getType())) {
									((ArrayList<IBasicObjectCore>)currentType.getValue("listOfElements")).add(boc);
								}
							}
							createdBoc.update(internalFrameBOC.getTree());
						}
					});
			}
		};
		ActionRedefine.getInstance().setAction(createStringActionCommand(internalFrameStructure.getClass(),"add_boc",internalFrameStructure.id), actionAddBOC);
		internalFrameStructure.setResizable(true);
		internalFrameStructure.setBounds(322, 0, 228, 261);
		layeredPane.add(internalFrameStructure);
		internalFrameStructure.pack();
		internalFrameStructure.setVisible(true);
		
		internalFrameBOC = new InternalFrameListOfBOC(SystemDataCore.getInstance().getBOC());
		AbstractAction actionSave = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				SystemDataCore.getInstance().saveListOfBoc();
			}
		};
		ActionRedefine.getInstance().setAction(createStringActionCommand(internalFrameBOC.getClass(),"save_boc",internalFrameBOC.id), actionSave);
		AbstractAction actionEdit = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				JTree tree = internalFrameBOC.getTree();
				if (tree.getLastSelectedPathComponent() != null) {
					CreateModelBOC createdBoc = new CreateModelBOC();
					DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree
							.getLastSelectedPathComponent();
					createdBoc.setData((IBasicObjectCore) parentNode.getUserObject());
					createdBoc.pack();
					createdBoc.setVisible(true);
					createdBoc.setActionCreateBOC(new AbstactEditorDialog.ActionCreateBOC() {
						public void create(IBasicObjectCore boc) {
							DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) tree
									.getLastSelectedPathComponent();
							IBasicObjectCore tmp = ((IBasicObjectCore) parentNode.getUserObject());
							tmp.setName(boc.getName());
							for(String head:boc.getHeader()) {
								tmp.setValue(head, boc.getValue(head));
							}
							createdBoc.update(internalFrameBOC.getTree());
						}
					});
				} else {
					SystemDataCore.getInstance().getLoggeurShell().printLog("Aucune sélection !", "red");
				}
			}
		};
		ActionRedefine.getInstance().setAction(createStringActionCommand(internalFrameBOC.getClass(),"edit_boc",internalFrameBOC.id), actionEdit);
		AbstractAction actionDelete = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				JTree tree = internalFrameBOC.getTree();
				if (tree.getLastSelectedPathComponent() != null) {
					DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree
							.getLastSelectedPathComponent();
					IBasicObjectCore selectTmp = (IBasicObjectCore) selectNode.getUserObject();
					DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) ((DefaultMutableTreeNode) tree
							.getLastSelectedPathComponent()).getParent();
					ArrayList<IBasicObjectCore> listTmp = (ArrayList<IBasicObjectCore>) ((IBasicObjectCore) parentNode.getUserObject()).getValue("listOfElements");
					listTmp.remove(selectTmp);
					tree.setModel(
							new DefaultTreeModel(new BuilderMutableTreeNode("root").addAutoBuildTree(SystemDataCore.getInstance().getBOC())));
					tree.revalidate();
				} else {
					SystemDataCore.getInstance().getLoggeurShell().printLog("Aucune sélection !", "red");
				}
			}
		};
		ActionRedefine.getInstance().setAction(createStringActionCommand(internalFrameBOC.getClass(),"delete_boc",internalFrameBOC.id), actionDelete);
		internalFrameBOC.setResizable(true);
		internalFrameBOC.setBounds(79, 11, 228, 261);
		layeredPane.add(internalFrameBOC);
		internalFrameBOC.pack();
		internalFrameBOC.setVisible(true);
		
		internalFrameLog = new InternalFrameLogShell();
		internalFrameLog.setAlignmentX(Component.LEFT_ALIGNMENT);
		internalFrameLog.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		internalFrameLog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		internalFrameLog.setAutoscrolls(true);
		internalFrameLog.setResizable(true);
		internalFrameLog.setBounds(10, 169, 374, 81);
		layeredPane.add(internalFrameLog);
		internalFrameLog.setVisible(true);
		SystemDataCore.getInstance().setLoggeurShell(internalFrameLog);
	}

	private IBasicObjectCore instanceData() {
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
		core.setName("engine");
		core.setValue("world", listGalaxy0);
		return core;
	}
}
