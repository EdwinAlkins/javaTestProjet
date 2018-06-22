package edwin.alkins.swingTest.gameSSS.ihm.component.logshell;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class InternalFrameLogShell extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8806273932419574393L;
	private JTextPane jtp_log;
	private JTextPane jtp_shell;

	/**
	 * Create the frame.
	 */
	public InternalFrameLogShell() {
		setBounds(100, 100, 557, 290);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JScrollPane srp_log = new JScrollPane();
		jtp_log = new JTextPane();
		jtp_log.setEditable(false);
		jtp_log.setContentType("text/html");
		srp_log.setViewportView(jtp_log);
		tabbedPane.addTab("log", null, srp_log, null);

		JPanel panelShell = new JPanel();
		JScrollPane srp_shell = new JScrollPane();
		jtp_shell = new JTextPane();
		jtp_shell.setEditable(false);
		jtp_shell.setContentType("text/html");
		srp_shell.setViewportView(jtp_shell);

		tabbedPane.addTab("shell", null, panelShell, null);
		panelShell.setLayout(new BorderLayout(0, 0));

		panelShell.add(srp_shell);

		JTextArea jta_editShell = new JTextArea();
		panelShell.add(jta_editShell, BorderLayout.SOUTH);
	}

	public void printLog(String str, String color) {
		StringBuilder buildSomething = new StringBuilder();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String log = new StringBuffer().append("<span style=\"color: ").append(color)
				.append(";font-family: verdana;font-size: 8px;\">").append(dateFormat.format(date)).append("\t")
				.append(str).append("</span><br>").toString();
		buildSomething.append(log);
		HTMLDocument doc = (HTMLDocument) jtp_log.getStyledDocument();
		try {
			doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), buildSomething.toString());
		} catch (BadLocationException | IOException e) {
			String messageError = e.getMessage();
			jtp_log.setText(messageError);
		}
	}
}
