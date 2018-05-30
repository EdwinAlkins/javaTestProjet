package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.BuilderJDOMboc;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IactionShip;
import edwin.alkins.swingTest.gameSSS.ihm.WindowAppTestV1;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class TestScript extends JFrame {

	private JPanel contentPane;
	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private JTextArea txtActionResult;
	private static final String RES = "/script/";
	private JPanel panel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new TestScript();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public TestScript() {
		
		System.out.println(BasicObjectCore.class.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		txtActionResult = new JTextArea();
		try {
			StringBuffer strb = new StringBuffer();
			BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + RES + "actionShip.js")));
			String line;
			while ((line = br.readLine()) != null) {
				strb.append(line+'\n');
			}
			br.close();
			txtActionResult.setText(strb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JScrollPane containeurTxtActionResult = new JScrollPane(txtActionResult);
		contentPane.add(containeurTxtActionResult, BorderLayout.CENTER);
		
		panel = new JPanel();
		containeurTxtActionResult.setColumnHeaderView(panel);
		
		btnNewButton = new JButton("Modifier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileWriter fichier;
				try {
					fichier = new FileWriter(new File(System.getProperty("user.dir") + RES + "actionShip.js"));
					fichier.write (txtActionResult.getText());
				    fichier.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);
	}
}
