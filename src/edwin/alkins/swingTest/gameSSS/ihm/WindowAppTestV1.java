package edwin.alkins.swingTest.gameSSS.ihm;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JFrame;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.BuilderJDOMboc;
import edwin.alkins.swingTest.gameSSS.core.basicObj.ReaderJDOMboc;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class WindowAppTestV1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowAppTestV1 window = new WindowAppTestV1();
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
	public WindowAppTestV1() {
		initialize();
		instance();
	}

	private void instance() {
		BasicObjectCore arme0 = new BasicObjectCore("arme");
		arme0.setValue("name", new String("blasteur"));
		arme0.setValue("atk", new Integer(10));
		arme0.setValue("vitesse", new Float(20f));
		
		BasicObjectCore arme1 = new BasicObjectCore("arme");
		arme1.setValue("name", new String("canion"));
		arme1.setValue("atk", new Integer(100));
		arme1.setValue("vitesse", new Float(10f));
		
		BasicObjectCore vaiseau0 = new BasicObjectCore("vaiseau");
		vaiseau0.setValue("name", new String("torpido"));
		vaiseau0.setValue("size_stockage", new Integer(1000));
		vaiseau0.setValue("arme", arme0);
		
		BasicObjectCore vaiseau1 = new BasicObjectCore("vaiseau");
		vaiseau1.setValue("name", new String("vitrio"));
		vaiseau1.setValue("size_stockage", new Integer(100));
		vaiseau1.setValue("arme", arme1);
		
		List<BasicObjectCore> listVaiseau = new ArrayList<>();
		listVaiseau.add(vaiseau0);
		listVaiseau.add(vaiseau1);
		
		BasicObjectCore secteur0 = new BasicObjectCore("secteur");
		secteur0.setValue("id", new Integer(0));
		secteur0.setValue("name", new String("secteur de naissance"));
		secteur0.setValue("list_vaiseaux", listVaiseau);
		
		new BuilderJDOMboc(secteur0);
		ReaderJDOMboc rboc = new ReaderJDOMboc("bdd_test1.xml");
		BasicObjectCore save = rboc.getSave();
		ArrayList<BasicObjectCore> list = (ArrayList<BasicObjectCore>)save.getValue("list_el");
		System.out.println(list.size());
		list.forEach(new Consumer<BasicObjectCore>() {
			@Override
			public void accept(BasicObjectCore t) {
				if(t.getValue("op").equals(274108)) {
					System.out.println(t);
				}
			}
		});
		
		/*BasicObjectCore o = new BasicObjectCore("bdd_test");
		ArrayList<GeneticObjectBusiness> initDataTable = AccessBD.getInstance().getActDAO().initDataTable();
		ArrayList<BasicObjectCore> bs = new ArrayList<>();
		for(GeneticObjectBusiness b:initDataTable) {
			BasicObjectCore bzzz = new BasicObjectCore("prod");
			for(String ss:b.getHeader())
				bzzz.setValue(ss, (b.getValue(ss)==null)?"":b.getValue(ss));
			bs.add(bzzz);
		}
		o.setValue("list_el",bs);
		new BuilderJDOMboc(o);*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JComboBox cb_listElement = new JComboBox();
		panel.add(cb_listElement);
		
		JButton btn_rechercher = new JButton("rechercher");
		panel.add(btn_rechercher);
		
		JButton btn_newboc = new JButton("new BOC");
		btn_newboc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							creatBoc frame = new creatBoc();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(btn_newboc);
		
		JTextPane txtp_info = new JTextPane();
		txtp_info.setEditable(false);
		frame.getContentPane().add(txtp_info, BorderLayout.CENTER);
	}

}
