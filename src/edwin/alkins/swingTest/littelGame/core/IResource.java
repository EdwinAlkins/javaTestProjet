package edwin.alkins.swingTest.littelGame.core;

public interface IResource {

	void updateQuantity(Long timePassOnMilis);

	double getQuantityOfRessource();
	
	String getName();

}