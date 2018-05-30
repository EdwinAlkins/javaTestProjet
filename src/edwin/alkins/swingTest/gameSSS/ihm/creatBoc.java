package edwin.alkins.swingTest.gameSSS.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.BuilderJDOMboc;

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
import java.awt.event.ActionEvent;

public class creatBoc extends JFrame {

	private JPanel contentPane;
	private ScriptEngineManager manager;
	private ScriptEngine engine;
	private JTextArea txtActionResult;
	public JTextArea txtScriptArea;

	/**
	 * Create the frame.
	 */
	public creatBoc() {
		
		initScript();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		txtScriptArea = new JTextArea();
		txtScriptArea.setText("function newBoc(boc) {\r\n\r\n\treturn boc;\r\n}");
		JScrollPane containeurTxtScriptArea = new JScrollPane(txtScriptArea);
		containeurTxtScriptArea.setBounds(10, 11, 378, 229);
		layeredPane.add(containeurTxtScriptArea);
		
		JButton btnAction = new JButton("action");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventAction) {
				try {
					engine.eval(txtScriptArea.getText());
					Invocable inv = (Invocable) engine;
			        Object result = inv.invokeFunction("newBoc", new BasicObjectCore());
			        txtActionResult.setText(result.toString());
			        new BuilderJDOMboc((BasicObjectCore)result);
				} catch (ScriptException | NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		});
		btnAction.setBounds(436, 12, 89, 23);
		layeredPane.add(btnAction);
		
		txtActionResult = new JTextArea();
		JScrollPane containeurTxtActionResult = new JScrollPane(txtActionResult);
		containeurTxtActionResult.setBounds(398, 49, 150, 191);
		layeredPane.add(containeurTxtActionResult);
	}

	private void initScript() {
		this.manager = new ScriptEngineManager();
		this.engine = this.manager.getEngineByName("JavaScript");
	}
}
