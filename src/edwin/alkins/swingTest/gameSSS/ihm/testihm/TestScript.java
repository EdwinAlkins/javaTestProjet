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
	private static final String RES = "/edwin/alkins/swingTest/gameSSS/ressources/data/script/";

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
		JScrollPane containeurTxtActionResult = new JScrollPane(txtActionResult);
		contentPane.add(containeurTxtActionResult, BorderLayout.CENTER);
			initScript();
	}

	private void initScript() {
		
	}

}
