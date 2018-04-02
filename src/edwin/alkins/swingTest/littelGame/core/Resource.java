package edwin.alkins.swingTest.littelGame.core;

import java.util.Map;

import edwin.alkins.swingTest.littelGame.exception.ExeptionLowResource;

public class Resource implements IResource {

	private final String name;
	private float multiplicatorOfProduction;
	private int currentLvl;
	private double quantity;
	private int nomberOfUsine;
	private float multiplicatorOfUpgrade;
	private ManagerProduction resource;
	private int useMinerauxToUpgrade;
	private String resourceUse;
	
	public Resource(String name, float multiplicator, ManagerProduction resource) {
		this.name = name;
		this.multiplicatorOfProduction = multiplicator;
		this.quantity = 0d;
		this.currentLvl = 1;
		this.nomberOfUsine = 1;
		this.resource = resource;
		this.useMinerauxToUpgrade = 50;
	}
	
	public void setMultiplicatorOfUpgrade(float multiplicator, String resourceUse) {
		this.multiplicatorOfUpgrade = multiplicator;
		this.resourceUse = resourceUse;
	}
	
	public synchronized void upgrade() throws ExeptionLowResource {
		this.resource.getResource().get(resourceUse).use(this.multiplicatorOfUpgrade*this.useMinerauxToUpgrade);
		this.currentLvl++;
	}
	
	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.littelGame.core.IResource#updateQuantity(java.lang.Long)
	 */
	@Override
	public synchronized void updateQuantity(Long timePassOnMilis) {
		this.quantity += new Double(multiplicatorOfProduction*currentLvl)*new Double(timePassOnMilis)*0.001d/60d * new Double(nomberOfUsine);
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

	@Override
	public void use(float f) throws ExeptionLowResource {
		if(this.quantity - f < 0) throw new ExeptionLowResource();
	}
}
