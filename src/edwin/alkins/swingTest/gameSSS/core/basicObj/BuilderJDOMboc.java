package edwin.alkins.swingTest.gameSSS.core.basicObj;

import java.io.FileOutputStream;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class BuilderJDOMboc {

	private Element racine;
	private volatile static int id = 0;
	private Document document;
	private static final String RES = "/edwin/alkins/swingTest/gameSSS/ressources/data/saveboc/";

	public BuilderJDOMboc(BasicObjectCore boc) {
		this.racine = new Element(boc.getName());
		Attribute type = new Attribute("type", boc.getClass().getName());
		Attribute name = new Attribute("name", ((BasicObjectCore) boc).getName());
		racine.setAttribute(type);
		racine.setAttribute(name);
		
		this.document = new Document(racine);
		buildDom(boc, racine);
		id++;
		enregistre(this.getClass().getResource(RES).getPath()+boc.getName()+id+".xml");
	}

	private Element buildDom(BasicObjectCore boc, Element father) {
		for (String key : boc.getHeader()) {
			Element element = new Element(key);
			Object o = boc.getValue(key);
			if(o instanceof BasicObjectCore) {
				Element child = new Element(key);
				Attribute type = new Attribute("type", o.getClass().getName());
				Attribute name = new Attribute("name", ((BasicObjectCore) o).getName());
				child.setAttribute(type);
				child.setAttribute(name);
				element = buildDom((BasicObjectCore) o,child);
			}
			else if(o instanceof List) {
				Attribute type = new Attribute("type", o.getClass().getName());
				element.setAttribute(type);
				for(Object ol:(List)o) {
					if(ol instanceof BasicObjectCore) {
						Element child = new Element(((BasicObjectCore) ol).getName());
						Attribute oltype = new Attribute("type", ol.getClass().getName());
						Attribute name = new Attribute("name", ((BasicObjectCore) ol).getName());
						child.setAttribute(oltype);
						child.setAttribute(name);
						element.addContent(buildDom((BasicObjectCore) ol,child));
					}
				}
			}
			else {
				Attribute type = new Attribute("type", o.getClass().getName());
				element.setAttribute(type);
				element.setText(o.toString());
			}
			father.addContent(element);
		}
		return father;
	}

	private void affiche() {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, System.out);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	private void enregistre(String fichier) {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(fichier));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
