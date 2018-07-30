package edwin.alkins.swingTest.littelGame2.core.item;

public abstract class Item {
	private final int id;
	private final String name;
	protected final int weight;
	
	public Item(int id, String name, int weight) {
		this.id = id;
		this.name = name;
		this.weight = weight;
	}
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getWeight() {
		return this.weight;
	}
}
