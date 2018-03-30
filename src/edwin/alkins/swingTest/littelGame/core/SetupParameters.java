package edwin.alkins.swingTest.littelGame.core;

public class SetupParameters {

	private ManagerProduction production;

	public SetupParameters() {
		this.production = new ManagerProduction();
		this.production.setDaemon(true);
		this.production.start();
	}

	public ManagerProduction getProduction() {
		return production;
	}
}
