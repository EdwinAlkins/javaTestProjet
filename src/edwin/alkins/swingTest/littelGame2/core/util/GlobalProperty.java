package edwin.alkins.swingTest.littelGame2.core.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalProperty {

	private static GlobalProperty instance = null;
	private Map<String,Object> property = new LinkedHashMap<>();
	public int performance = 1;

	private GlobalProperty() {
	}
	public static GlobalProperty getInstance() {
		if (instance == null)
			instance = new GlobalProperty();
		return instance;
	}
	public void setProperty(String key, Object value) {
		if(property.containsKey(key))
			this.property.replace(key, value);
		else
			this.property.put(key, value);
	}
	public Object getProperty(String key) {
		return this.property.getOrDefault(key, new Object());
	}
	public boolean getBooleanProperty(String key) {
		return (boolean)this.property.getOrDefault(key, false);
	}
}
