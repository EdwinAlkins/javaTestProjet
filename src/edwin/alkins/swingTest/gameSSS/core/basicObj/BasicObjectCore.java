package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class BasicObjectCore {

	protected HashMap<String,Object> attributs;
	protected String name;
 
	public BasicObjectCore() {
		this.attributs = new HashMap<String,Object>();
		this.name = "defautBOC";
	}
	
	public BasicObjectCore(String name) {
 		this.attributs = new HashMap<String,Object>();
 		if(name == null || name.isEmpty())
 			this.name = "defautBOC";
 		else
 			this.name = name;
 	}
 	
 	public Object getValue(String key) {
 		if(this.attributs.containsKey(key))
 			return this.attributs.get(key);
 		else 
 			return null;
 	}
 	
 	public List<String> getHeader(){
 		List<String> header = new ArrayList<>();
 		for(Entry<String, Object> element:attributs.entrySet()) {
 			header.add(element.getKey());
 		}
 		return header;
 	}
 	
 	public List<Object> getElements(){
 		List<Object> elements = new ArrayList<>();
 		for(Entry<String, Object> element:attributs.entrySet()) {
 			elements.add(element.getValue());
 		}
 		return elements;
 	}
 	
 	public Object getAt(int index) {
 		return getElements().get(index);
 	}

	public void setValue(String key, Object value) {
		if(this.attributs.containsKey(key))
			this.attributs.replace(key, value);
		else
			this.attributs.put(key, value);
	}
	
 	public String getName() {
		return name;
	}
 	
 	public void setName(String name) {
		this.name = name;
	}
	
 	@Override
	public String toString() {
		return "["+this.name + "=" + attributs+"]";
	}
}
