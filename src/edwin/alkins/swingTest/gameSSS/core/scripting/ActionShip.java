package edwin.alkins.swingTest.gameSSS.core.scripting;

import edwin.alkins.swingTest.gameSSS.core.basicObj.IBasicObjectCore;
import edwin.alkins.swingTest.gameSSS.core.basicObj.IactionShip;

public class ActionShip implements IactionShip {

	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IactionShip#action(edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore)
	 */
	@Override
	public void action(IBasicObjectCore core) {
		System.out.println(core);
	}
}
