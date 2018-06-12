package edwin.alkins.swingTest.gameSSS.ihm.testihm;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;

public class BuilderMutableTreeNode extends DefaultMutableTreeNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5801456597301835911L;

	public BuilderMutableTreeNode(Object userObject) {
		super(userObject);
	}
	
	public BuilderMutableTreeNode(IBasicObjectCore core) {
		super(core);
	}
	
	public BuilderMutableTreeNode(Object userObject, boolean allowsChildren) {
		super(userObject, allowsChildren);
	}
	
	public BuilderMutableTreeNode() {
		super();
	}
	
	public BuilderMutableTreeNode addBuilderTreeNode(DefaultMutableTreeNode node) {
		super.add(node);
		return this;
	}
	
	public BuilderMutableTreeNode addAutoBuildTree(IBasicObjectCore boc) {
		BuilderMutableTreeNode father = new BuilderMutableTreeNode(boc);
		for(String key:boc.getHeader()) {
			Object obj = boc.getValue(key);
			if(obj instanceof IBasicObjectCore) {
				father.addAutoBuildTree((IBasicObjectCore)obj);
			}
			else if(obj instanceof List<?> && !((List<?>)obj).isEmpty() && ((List<?>)obj).get(0) instanceof IBasicObjectCore){
				for(Object o:(List<?>)obj) {
					father.addAutoBuildTree((IBasicObjectCore)o);
				}
			}
		}
		return this.addBuilderTreeNode(father);
	}
}
