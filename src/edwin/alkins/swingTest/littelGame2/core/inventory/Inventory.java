package edwin.alkins.swingTest.littelGame2.core.inventory;

import java.util.LinkedHashMap;

import edwin.alkins.swingTest.littelGame2.core.exeption.ExceptionMaxCapacity;
import edwin.alkins.swingTest.littelGame2.core.item.Item;

public class Inventory {
	private LinkedHashMap<Item, Integer> items;  
	protected long capacity;
	private long currentWeight;
	
	public Inventory(long capacity) {
		this.items = new LinkedHashMap<>();
		this.capacity = capacity;
		this.currentWeight = 0;
	}
	public void addItem(Item item, int qte) throws ExceptionMaxCapacity{
		if(items.containsKey(item)) {
			if(currentWeight + item.getWeight()*qte <= capacity) {
				items.replace(item,items.get(item)+qte);
				this.currentWeight += item.getWeight()*qte;
			}
			else
				throw new ExceptionMaxCapacity();
		}
		items.put(item, qte);
	}
	public int lookItem(Item item) {
		if(items.containsKey(item))
			return items.get(item);
		return 0;
	}
	public int getItem(Item item, int qte) {
		if(items.containsKey(item)) {
			int differance = items.get(item)-qte;
			if(differance == 0) {
				items.remove(item);
				return qte;
			}
			else if(differance < 0) {
				items.remove(item);
				return qte + differance;
			}
			items.replace(item,differance);
			return qte;
		}
		return 0;
	}
}
