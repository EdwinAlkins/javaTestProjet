package edwin.alkins.swingTest.littelGame.core;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edwin.alkins.swingTest.littelGame.exception.ExceptionCantAddJComponentInFrame;
import edwin.alkins.swingTest.littelGame.exception.ExceptionComponentIsAlreadyInTheContainer;
import edwin.alkins.swingTest.littelGame.exception.ExceptionComponentIsNotInTheContainer;
import edwin.alkins.swingTest.littelGame.ihm.JPanelError;
import edwin.alkins.swingTest.littelGame.ihm.JPanelLoading;

public class ManagerIHM {

	private Map<String,JComponent> containerOfComponent;
	private Map<String,String> containerOfCurrentComponent;
	private Map<String,String> containerOfFrame;
	private JFrame currentFrame;
	private static ManagerIHM instance = null;
	
	public static ManagerIHM getInstance() {
		if(instance == null)
			instance = new ManagerIHM();
		return instance;
	}
	
	private ManagerIHM() {
		this.containerOfComponent = new HashMap<>();
		this.containerOfCurrentComponent = new HashMap<>();
		this.containerOfFrame = new HashMap<>();
		initializingTheBasicComponents();
	}

	private void initializingTheBasicComponents() {
		this.containerOfComponent.put(JPanelError.class.getName(), new JPanelError());
		this.containerOfComponent.put(JPanelLoading.class.getName(), new JPanelLoading());
	}
	
	public void addComponent(String key, JComponent component) throws ExceptionComponentIsAlreadyInTheContainer {
		if(this.containerOfComponent.containsKey(key))
			throw new ExceptionComponentIsAlreadyInTheContainer();
		else {
			this.containerOfComponent.put(key, component);
		}
	}
	
	public void addComponentOfFrame(String keyOfParent, String keyOfPanel) throws ExceptionComponentIsAlreadyInTheContainer, ExceptionComponentIsNotInTheContainer {
		if(this.containerOfFrame.containsKey(keyOfParent))
			throw new ExceptionComponentIsAlreadyInTheContainer();
		else {
			JComponent jpanel = this.getComponent(keyOfPanel);
			if(jpanel instanceof JPanel) {
				if (this.containerOfFrame.containsKey(keyOfPanel)) {
					throw new ExceptionComponentIsAlreadyInTheContainer();
				} 
				else {
					 this.containerOfFrame.put(keyOfParent, keyOfPanel);
				}
			}
		}
	}
	
	public JComponent getComponent(String key) throws ExceptionComponentIsNotInTheContainer {
		if(this.containerOfComponent.containsKey(key))
			return this.containerOfComponent.get(key);
		else
			throw new ExceptionComponentIsNotInTheContainer();
	}
	
	public void setFrame(JFrame frame) {
		this.currentFrame = frame;
	}
	
	public void removeComponentOfTheIHM(String key) throws ExceptionComponentIsNotInTheContainer {
		if(!this.containerOfCurrentComponent.containsKey(key))
			throw new ExceptionComponentIsNotInTheContainer();
		else{
			JComponent component = this.getComponent(this.containerOfCurrentComponent.get(key));
			component.invalidate();
			component.getParent().remove(component);
			containerOfCurrentComponent.remove(key);
			component.getParent().revalidate();
		}
	}
	
	public void replaceComponentOfTheIHM(String keyOfComponentOld, String keyOfComponentNew, Object constraints) throws ExceptionComponentIsNotInTheContainer,ExceptionCantAddJComponentInFrame {
		if(!this.containerOfCurrentComponent.containsKey(keyOfComponentOld)) {
			if(this.containerOfFrame.containsKey(keyOfComponentOld)) {
				String keyOfParent = this.containerOfFrame.get(keyOfComponentOld);
				JComponent componentSon = this.getComponent(keyOfComponentNew);
				JComponent componentParent = this.getComponent(keyOfParent);
				if(!(componentSon instanceof JPanel))
					throw new ExceptionCantAddJComponentInFrame();
				else {
					this.currentFrame.remove(componentParent);
					this.currentFrame.add(componentSon);
					currentFrame.revalidate();
				}
			}
			else
				throw new ExceptionComponentIsNotInTheContainer();
		}
		else{
			JComponent component = this.getComponent(keyOfComponentNew);
			component.add(component, constraints);
		}
	}
}
