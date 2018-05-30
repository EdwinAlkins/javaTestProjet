package edwin.alkins.swingTest.gameSSS.core.basicObj;

public class ActionShip implements IactionShip {

	/* (non-Javadoc)
	 * @see edwin.alkins.swingTest.gameSSS.core.basicObj.IactionShip#action(edwin.alkins.swingTest.gameSSS.core.basicObj.BasicObjectCore)
	 */
	@Override
	public void action(IBasicObjectCore core) {
		System.out.println(core);
	}
}
