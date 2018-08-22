package edwin.alkins.swingTest.littelGame2.core.main;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edwin.alkins.swingTest.littelGame2.core.game.GameLoop;

public class Maine1 {

	/**
	 * http://fivedots.coe.psu.ac.th/~ad/jg/
	 * https://members.loria.fr/VThomas/mediation/JV_IUT_2016/2016_IUT_boucle_jeu.pdf
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("performance", "1");
		System.setProperty("fullScrean", "false");
		System.setProperty("isFrame", "false");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread t = new Thread(new GameLoop());
					t.setDaemon(true);
					t.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
