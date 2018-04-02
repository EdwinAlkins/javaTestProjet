package edwin.alkins.swingTest.littelGame.core;

import edwin.alkins.swingTest.littelGame.exception.ExeptionLowResource;

public interface IResource {

	void updateQuantity(Long timePassOnMilis);

	double getQuantityOfRessource();
	
	String getName();

	void use(float f) throws ExeptionLowResource;

}