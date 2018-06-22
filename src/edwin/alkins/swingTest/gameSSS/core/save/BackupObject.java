package edwin.alkins.swingTest.gameSSS.core.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BackupObject<T> {

	@SuppressWarnings("unchecked")
	public T load(File f) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			T obj = (T) ois.readObject();
			ois.close();
			return obj;
		} catch (ClassNotFoundException exception) {
			System.out.println("Impossible de lire l'objet : " + exception.getMessage());
		} catch (IOException exception) {
			System.out.println("Erreur lors de l'écriture : " + exception.getMessage());
		}
		return null;
	}
	
	public boolean save(T obj, File f) {
		boolean success = false;
		try {
			if(!f.exists()) {
				f.getParentFile().mkdirs();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(obj);
			oos.close();
			success = true;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return success;
	}
}
