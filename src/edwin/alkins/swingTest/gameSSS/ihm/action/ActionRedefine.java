package edwin.alkins.swingTest.gameSSS.ihm.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;

public class ActionRedefine extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7235987681232597246L;
	
	private static int idActionIncrement = 0;
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
		System.out.println(action.getActionCommand());
		if(mapAction.containsKey(action.getActionCommand()))
			mapAction.get(action.getActionCommand()).actionPerformed(action);
	}
	
	public void setAction(String key, AbstractAction action) {
		mapAction.put(key, action);
	}
	
	public synchronized int getIdAction() {
		return idActionIncrement++;
	}
	
	public static String createStringActionCommand(final Class<?> c, final String name, int idOfContext) {
		return new StringBuffer().append(c.getSimpleName()).append('[').append(idOfContext).append(']').append(name).toString();
	}
}
