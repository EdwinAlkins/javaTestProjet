package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.ReaderJDOMboc;
import edwin.alkins.swingTest.gameSSS.ihm.component.table.TabelModelGenerique;
import edwin.alkins.swingTest.gameSSS.ihm.component.table.TableHeaderSorter;
import edwin.alkins.swingTest.gameSSS.ihm.component.table.TableSorter;

import java.awt.BorderLayout;

public class DisplayXMLToTable {

	private JFrame frame;
	private JTable table;
	private TabelModelGenerique model;
	private TableSorter sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayXMLToTable window = new DisplayXMLToTable();
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
	public DisplayXMLToTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model = new TabelModelGenerique();
		model.setData(new ArrayList<BasicObjectCore>());
		sorter = new TableSorter(model);
		table = new JTable(sorter);
		new TableHeaderSorter().install(sorter, table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		SwingWorker sw = new SwingWorker<ArrayList<BasicObjectCore>,BasicObjectCore>() {
			protected ArrayList<BasicObjectCore> doInBackground() throws Exception {
				ReaderJDOMboc rboc = new ReaderJDOMboc("bdd_test1.xml");
				IBasicObjectCore save = rboc.getSave();
				return (ArrayList<BasicObjectCore>)save.getValue("list_el");
			}
			public void done(){
				try {
					model.setData(get());
					sorter.sortByColumn(model.getColumnid("date"), false);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		sw.execute();
	}

}
