package edwin.alkins.swingTest.littelGame.core;

import java.util.Map;

public class Resource implements IResource {

	private final String name;
	private final Map<Integer,Integer> productionDependingOnTheLevel;
	private int currentLvl;
	private double quantity;
	
	public Resource(String name, Map<Integer,Integer> production) {
		this.name = name;
		this.productionDependingOnTheLevel = production;
		this.quantity = 0d;
		this.currentLvl = 0;
	}
	
	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.littelGame.core.IResource#updateQuantity(java.lang.Long)
	 */
	@Override
	public void updateQuantity(Long timePassOnMilis) {
		this.quantity += new Double(this.productionDependingOnTheLevel.get(this.currentLvl))*timePassOnMilis*0.001;
	}
	
	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.littelGame.core.IResource#getQuantityOfRessource()
	 */
	@Override
	public double getQuantityOfRessource() {
		return this.quantity;
	}

	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.littelGame.core.IResource#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
}
