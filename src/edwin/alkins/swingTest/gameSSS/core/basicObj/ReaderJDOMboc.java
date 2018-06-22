package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
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
			document = sxb.build(this.getClass().getResourceAsStream(RES+name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		racine = document.getRootElement();
	}

	public IBasicObjectCore getSave() {
		try {
			String type = racine.getAttribute("type").getValue();
			if (type.equals(BasicObjectCore.class.getName())) {
				String name = racine.getAttribute("name").getValue();
				IBasicObjectCore boc = new BasicObjectCore(name);
				return nextReaderSave(boc,racine);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | IOException e) {
			e.printStackTrace();
		}
		return new BasicObjectCore();
	}
	
	public IBasicObjectCore getStructure() {
		IBasicObjectCore strucutredBOC = new BasicObjectCore();
		strucutredBOC.setName("structure");
		strucutredBOC.setType(IBasicObjectCore.class.getName());
		nextReaderStructure(strucutredBOC,racine.getChildren("element"));
		return strucutredBOC;
	}
	
	private IBasicObjectCore nextReaderStructure(IBasicObjectCore boc, List<Element> listElement){
		for(Element element:listElement) {
			BasicObjectCore tmp = new BasicObjectCore(element.getAttributeValue("type"));
			tmp.setValue("type",element.getAttributeValue("type"));
			tmp.setValue("sous_type",element.getAttributeValue("sous_type"));
			boc.setValue(element.getAttributeValue("type"), tmp);
			for(Element attribut:element.getChildren("attribut")) {
				BasicObjectCore att = new BasicObjectCore(attribut.getAttributeValue("name")); 
				att.setValue("name",attribut.getAttributeValue("name"));
				att.setValue("type",attribut.getAttributeValue("type"));
				att.setValue("sous_type",attribut.getAttributeValue("sous_type"));
				att.setValue("defaut",attribut.getAttributeValue("defaut"));
				tmp.setValue(attribut.getAttributeValue("name"), att);
			}
		}
		return boc;
	}

	private IBasicObjectCore nextReaderSave(IBasicObjectCore boc, Element el) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException, IOException {
		List listElement = el.getChildren();
		Iterator i = listElement.iterator();
		while (i.hasNext()) {
			Element courant = (Element) i.next();
			String param = courant.getValue();
			String type = courant.getAttribute("type").getValue();
			if (type.equals(BasicObjectCore.class.getName())) {
				String name = courant.getAttribute("name").getValue();
				IBasicObjectCore tmpBoc = new BasicObjectCore(name);
				boc.setValue(courant.getName(),nextReaderSave(tmpBoc,courant));
			}
			else if(type.equals(ArrayList.class.getName())) {
				List dataList = new ArrayList<>();
				List array_listElement = courant.getChildren();
				Iterator array_i = array_listElement.iterator();
				while (array_i.hasNext()) {
					Element array_el = (Element) array_i.next();
					String array_param = array_el.getValue();
					String array_type = array_el.getAttribute("type").getValue();
					if (array_type.equals(BasicObjectCore.class.getName())) {
						String name = array_el.getAttribute("name").getValue();
						IBasicObjectCore tmpBoc = new BasicObjectCore(name);
						//boc.setValue(array_el.getName(),next(tmpBoc,array_el));
						dataList.add(nextReaderSave(tmpBoc,array_el));
					}
					else
						dataList.add(getObj(array_type,array_param));
				}
				boc.setValue(courant.getName(), dataList);
			}
			else
				boc.setValue(courant.getName(),getObj(type,param));
		}
		return boc;
	}
	
	private Object getObj(String type, String value) {
		Object o = new Object();
		if(type.equals(String.class.getName()))
			o = new String(value);
		else if(type.equals(Integer.class.getName()))
			o = new Integer(value);
		else if(type.equals(Float.class.getName()))
			o = new Float(value);
		else if(type.equals(BigInteger.class.getName()))
			o = new BigInteger(value);
		else if(type.equals(Time.class.getName()))
			o = new Time(0).valueOf(value);
		else if(type.equals(Date.class.getName()))
			o = new Date(0).valueOf(value);
		else
			o = new String("ERROR");
		return o;
	}
}
