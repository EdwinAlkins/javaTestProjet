package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.io.File;

import edwin.alkins.swingTest.gameSSS.core.save.BackupObject;

public class SystemDataCore {

	private static SystemDataCore instance = null;
	public static SystemDataCore getInstance() {
		if(SystemDataCore.instance==null)
			SystemDataCore.instance = new SystemDataCore();
		return SystemDataCore.instance;
	}
	private IBasicObjectCore boc;
	private IBasicObjectCore structureBOC;
	private static final String RES = "/edwin/alkins/swingTest/gameSSS/ressources/data/saveboc/";
	
	private SystemDataCore() {
		File fileLoad = new File(this.getClass().getResource(RES).getPath() + "save.objet");
		if(fileLoad.exists())
			this.boc = new BackupObject<BasicObjectCore>().load(fileLoad);
		else
			this.boc = new BasicObjectCore();
		ReaderJDOMboc rboc = new ReaderJDOMboc("structure.xml");
		this.structureBOC = rboc.getStructure();
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
	public void saveListOfBoc() {
		File fileLoad = new File(this.getClass().getResource(RES).getPath() + "save.objet");
		new BackupObject<IBasicObjectCore>().save(this.boc, fileLoad);
	}
}
