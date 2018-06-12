package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.util.List;

public interface IBasicObjectCore {

	Object getValue(String key);

	List<String> getHeader();

	List<Object> getElements();

	Object getAt(int index);

	void setValue(String key, Object value);

	String getType();

	void setType(String name);
	
	String getName();
	
	void setName(String name);

	String toString();

	String getRepresentation();
}