package edwin.alkins.swingTest.gameSSS.ihm.component.dialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTree;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;

public abstract class AbstactEditorDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7589447350756302969L;

	protected final JPanel contentPanel = new JPanel();
	public interface ActionCreateBOC{
		public void create(IBasicObjectCore boc);
	}
	protected ActionCreateBOC action = null;
	public void setActionCreateBOC(ActionCreateBOC action) {
		this.action = action;
	}
	protected abstract ArrayList<String> getListType();
	public abstract void update(JTree tree);
}
