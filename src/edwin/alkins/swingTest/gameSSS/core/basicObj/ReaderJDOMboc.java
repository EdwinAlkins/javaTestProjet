package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.Iterator;

public class ReaderJDOMboc {

	private Element racine;
	private Document document;
	private static final String RES = "/edwin/alkins/swingTest/gameSSS/ressources/data/saveboc/";

	public ReaderJDOMboc(String name) {
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(this.getClass().getResource(RES).getPath() + name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		racine = document.getRootElement();
		try {
			firt();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
			e.printStackTrace();
		}
	}

	private void firt() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, IOException {
		String type = racine.getAttribute("type").getValue();
		if (type.equals(BasicObjectCore.class.getName())) {
			String name = racine.getAttribute("name").getValue();
			Object o = this.getClass().getClassLoader().loadClass(type).newInstance();
			for (Method fun : o.getClass().getMethods()) {
				if (fun.getName().equals("setName"))
					fun.invoke(o, name);
			}
			List listElement = racine.getChildren();
			Iterator i = listElement.iterator();
			while (i.hasNext()) {
				Element courant = (Element) i.next();
				next((BasicObjectCore)o,courant);
			}
			System.out.println(o);
		}
	}

	private BasicObjectCore next(BasicObjectCore boc, Element el) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException, IOException {
		String type = el.getAttribute("type").getValue();
		if (type.equals(BasicObjectCore.class.getName())) {
			String name = el.getAttribute("name").getValue();
			Object o = this.getClass().getClassLoader().loadClass(type).newInstance();
			for (Method fun : o.getClass().getMethods()) {
				if (fun.getName().equals("setName"))
					fun.invoke(o, name);
			}
			List listElement = el.getChildren();
			Iterator i = listElement.iterator();
			while (i.hasNext()) {
				Element courant = (Element) i.next();
				next((BasicObjectCore)o,courant);
			}
			boc.setValue(el.getName(), o);
		}
		else if(type.equals(ArrayList.class.getName())) {
			ArrayList<Object> list = new ArrayList<>();
			List listElement = racine.getChildren();
			Iterator i = listElement.iterator();
			while (i.hasNext()) {
				Element courant = (Element) i.next();
				String type1 = el.getAttribute("type").getValue();
				if (type1.equals(BasicObjectCore.class.getName())) {
					String name = el.getAttribute("name").getValue();
					Object o = this.getClass().getClassLoader().loadClass(type1).newInstance();
					for (Method fun : o.getClass().getMethods()) {
						if (fun.getName().equals("setName"))
							fun.invoke(o, name);
					}
					if(courant.getContentSize()>1)
						list.add(next((BasicObjectCore)o,courant));
					else 
						list.add(o);
				}
			}
		}
		else {
			String param = el.getValue();
			Object o = null;
			if(type.equals(String.class.getName()))
				o = new String(param);
			else if(type.equals(Integer.class.getName()))
				o = new Integer(param);
			else if(type.equals(String.class.getName()))
				o = new Float(param);
			boc.setValue(el.getName(), o);
		}
		return boc;
	}
}
