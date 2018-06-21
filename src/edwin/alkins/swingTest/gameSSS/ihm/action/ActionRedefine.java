package edwin.alkins.swingTest.gameSSS.ihm.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.AbstractAction;

public class ActionRedefine extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7235987681232597246L;

	private HashMap<String, AbstractAction> mapAction = new HashMap<>();
	
	private static ActionRedefine instance = null;
	private ActionRedefine() {}
	public static ActionRedefine getInstance() {
		if(instance == null)
			instance = new ActionRedefine();
		return instance;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		if(mapAction.containsKey(action.getActionCommand()))
			mapAction.get(action.getActionCommand()).actionPerformed(action);
	}
	
	public void setAction(String key, AbstractAction action) {
		mapAction.put(key, action);
	}
}
