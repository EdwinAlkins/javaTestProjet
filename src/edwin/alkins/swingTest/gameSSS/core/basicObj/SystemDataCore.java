package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import edwin.alkins.swingTest.gameSSS.core.save.BackupObject;
import edwin.alkins.swingTest.gameSSS.ihm.component.logshell.InternalFrameLogShell;

public class SystemDataCore {

	private static SystemDataCore instance = null;
	public static SystemDataCore getInstance() {
		if(SystemDataCore.instance==null)
			SystemDataCore.instance = new SystemDataCore();
		return SystemDataCore.instance;
	}
	private IBasicObjectCore boc;
	private IBasicObjectCore structureBOC;
	private InternalFrameLogShell loggeurShell;
	private static final String RES = "/edwin/alkins/swingTest/gameSSS/ressources/data/saveboc/";
	
	private SystemDataCore() {
		ReaderJDOMboc rboc = new ReaderJDOMboc("structure.xml");
		this.structureBOC = rboc.getStructure();
		File fileLoad = new File(this.getClass().getResource(RES).getPath() + "save.objet");
		if(fileLoad.exists())
			this.boc = new BackupObject<BasicObjectCore>().load(fileLoad);
		else
			this.boc = instanceListData();
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
	private IBasicObjectCore instanceListData() {
		ArrayList<IBasicObjectCore> listType = new ArrayList<>();
		for(String head:structureBOC.getHeader()) {
			BasicObjectCore tmpBoc = new BasicObjectCore(head);
			tmpBoc.setValue("listOfElements", new ArrayList<>());
			listType.add(tmpBoc);
		}
		IBasicObjectCore core = new BasicObjectCore("core");
		core.setValue("listOfType", listType);
		return core;
	}
	public void setLoggeurShell(InternalFrameLogShell internalFrameLog) {
		this.loggeurShell = internalFrameLog;
	}
	public InternalFrameLogShell getLoggeurShell() {
		return loggeurShell;
	}
}
