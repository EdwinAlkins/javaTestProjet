package edwin.alkins.swingTest.gameSSS.core.basicObj;

public class SystemDataCore {

	private static SystemDataCore instance = null;
	public static SystemDataCore getInstance() {
		if(SystemDataCore.instance==null)
			SystemDataCore.instance = new SystemDataCore();
		return SystemDataCore.instance;
	}
	private IBasicObjectCore boc;
	private IBasicObjectCore structureBOC;
	
	private SystemDataCore() {
		this.boc = new BasicObjectCore();
		this.structureBOC = new BasicObjectCore();
	}
	
	public void setBOC(IBasicObjectCore boc) {
		this.boc = boc;
	}
	public IBasicObjectCore getBOC() {
		return this.boc;
	}
	
	public void setStructuredBOC(IBasicObjectCore boc) {
		this.structureBOC = boc;
	}
	public IBasicObjectCore getStructuredBOC() {
		return this.structureBOC;
	}
}
