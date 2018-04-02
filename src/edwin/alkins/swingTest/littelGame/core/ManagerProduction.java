package edwin.alkins.swingTest.littelGame.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ManagerProduction extends Thread{

	public static final String MINERAUX = "Mineraux";
	public static final String NOURRITURE = "Nourriture";
	public static final String POPULATION = "Population";
	public static final String ENERGIE = "Energie";
	
	private long lastCurrentTime;
	private Map<String,IResource> resource;
	private volatile boolean isProduces;
	
	private Set<ProducesHandler> listOfActionOnProduction;
	public interface ProducesHandler{
		void resource(String name, double quantity);
	}
	
	public ManagerProduction() {
		this.resource = new HashMap<>();
		this.listOfActionOnProduction = new HashSet<>();
		IResource metal = initializationProductionOfMineraux();
		this.resource.put(metal.getName(),metal);
		IResource nourriture = initializationProductionOfNourriture();
		this.resource.put(nourriture.getName(),nourriture);
		IResource habitant = initializationProductionOfHabitant();
		this.resource.put(habitant.getName(),habitant);
		IResource energie = initializationProductionOfEnergie();
		this.resource.put(energie.getName(),energie);
		this.isProduces = true;
	}
	
	public void addHandleurOnProduction(ProducesHandler handleur) {
		this.listOfActionOnProduction.add(handleur);
	}

	private IResource initializationProductionOfMineraux() {
		return new Resource(MINERAUX, 2f, this);
	}
	
	private IResource initializationProductionOfNourriture() {
		return new Resource(NOURRITURE, 2f, this);
	}
	
	private IResource initializationProductionOfHabitant() {
		return new Resource(POPULATION, 1f, this);
	}
	
	private IResource initializationProductionOfEnergie() {
		return new Resource(ENERGIE, 2f, this);
	}
	
	public synchronized void stopProduction() {
		this.isProduces = false;
	}
	
	public synchronized void startProduction() {
		this.isProduces = true;
	}

	public Map<String, IResource> getResource() {
		return resource;
	}

	@Override
	public void run(){
		this.lastCurrentTime = System.currentTimeMillis();
		while(true) {
			redefineSleep(100l);
			while(!isProduces) {
				redefineSleep(100l);
			}
			Long timePassOnMilis =  System.currentTimeMillis() - lastCurrentTime;
			this.lastCurrentTime = System.currentTimeMillis();
			for(Entry<String, IResource> r:resource.entrySet()) {
				IResource currentResource = r.getValue();
				currentResource.updateQuantity(timePassOnMilis);
				for(ProducesHandler p:this.listOfActionOnProduction) {
					p.resource(currentResource.getName(), currentResource.getQuantityOfRessource());
				}
			}
		}
 	}
	
	private void redefineSleep(Long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
