package edwin.alkins.swingTest.gameSSS.ihm;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.BuilderJDOMboc;
import edwin.alkins.swingTest.gameSSS.core.basicObj.ReaderJDOMboc;

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
		BasicObjectCore boc0 = new BasicObjectCore("myObject0");
		boc0.setValue("string1", new String("valeur 1"));
		boc0.setValue("string2", new String("valeur 2"));
		
		BasicObjectCore boc1 = new BasicObjectCore("myObject1");
		boc1.setValue("int1", new Integer(1));
		boc1.setValue("int2", new Integer(2));
		
		BasicObjectCore boc2 = new BasicObjectCore("myObject2");
		boc2.setValue("int", new Integer(1));
		boc2.setValue("string", new String("une chaine de test"));
		boc2.setValue("float", new Float(10f));
		List<BasicObjectCore> listBoc = new ArrayList<>();
		listBoc.add(boc0);
		listBoc.add(boc1);
		boc2.setValue("list", listBoc);
		
		BasicObjectCore boc3 = new BasicObjectCore("myObject3");
		boc3.setValue("string", new String("test de la recursivite"));
		boc3.setValue("objBOC", boc2);
		new BuilderJDOMboc(boc3);
		new ReaderJDOMboc("myObject31.xml");
		System.out.println(boc3.toString());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
