package edwin.alkins.swingTest.littelGame.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ManagerProduction extends Thread{

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
		IResource metal = initializationProductionOfMetal();
		this.resource.put(metal.getName(),metal);
		this.isProduces = true;
	}
	
	public void addHandleurOnProduction(ProducesHandler handleur) {
		this.listOfActionOnProduction.add(handleur);
	}

	private IResource initializationProductionOfMetal() {
		Map<Integer,Integer> productionOfMetal = new HashMap<>();
		productionOfMetal.put(0, 10);
		productionOfMetal.put(1, 50);
		productionOfMetal.put(2, 100);
		productionOfMetal.put(3, 200);
		productionOfMetal.put(4, 300);
		productionOfMetal.put(5, 400);
		productionOfMetal.put(6, 500);
		productionOfMetal.put(7, 600);
		productionOfMetal.put(8, 700);
		productionOfMetal.put(9, 800);
		return new Resource("Metal", productionOfMetal);
	}
	
	public synchronized void stopProduction() {
		this.isProduces = false;
	}
	
	public synchronized void startProduction() {
		this.isProduces = true;
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