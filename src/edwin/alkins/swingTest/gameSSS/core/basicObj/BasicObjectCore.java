package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class BasicObjectCore implements IBasicObjectCore, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6943685216297941153L;
	protected HashMap<String,Object> attributs;
	protected String type;
	protected String name;
 
	public BasicObjectCore() {
		this.attributs = new HashMap<String,Object>();
		this.type = "java.lang.Object";
	}
	
	public BasicObjectCore(String name) {
 		this.attributs = new HashMap<String,Object>();
 		if(name == null || name.isEmpty())
 			this.type = "defautBOC";
 		else
 			this.type = name;
 	}
 	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#getValue(java.lang.String)
	 */
 	@Override
	public Object getValue(String key) {
 		if(this.attributs.containsKey(key))
 			return this.attributs.get(key);
 		else 
 			return null;
 	}
 	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#getHeader()
	 */
 	@Override
	public List<String> getHeader(){
 		List<String> header = new ArrayList<>();
 		for(Entry<String, Object> element:attributs.entrySet()) {
 			header.add(element.getKey());
 		}
 		return header;
 	}
 	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#getElements()
	 */
 	@Override
	public List<Object> getElements(){
 		List<Object> elements = new ArrayList<>();
 		for(Entry<String, Object> element:attributs.entrySet()) {
 			elements.add(element.getValue());
 		}
 		return elements;
 	}
 	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#getAt(int)
	 */
 	@Override
	public Object getAt(int index) {
 		return getElements().get(index);
 	}

	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#setValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setValue(String key, Object value) {
		if(this.attributs.containsKey(key))
			this.attributs.replace(key, value);
		else
			this.attributs.put(key, value);
	}
	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#getName()
	 */
 	@Override
	public String getType() {
		return type;
	}
 	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#setName(java.lang.String)
	 */
 	@Override
	public void setType(String type) {
		this.type = type;
	}
 	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
 	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore#toString()
	 */
 	@Override
	public String toString() {
 		String str = new String();
 		if(this.name!=null)
 			str = this.name+":["+this.type+"]";
 		else
 			str = this.type;
		return str;
	}
 	
 	public String getRepresentation() {
		return "("+this.type+ "=" + attributs+")";
	}
}
