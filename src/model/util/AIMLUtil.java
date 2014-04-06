package model.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Category;
import model.Entity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AIMLUtil {
	public static void loadAIML(Entity entity) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(entity.getName().trim() + ".aiml");
			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("category");

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					Category newCategory = new Category(getTagValue("pattern",
							element), getTagValue("template", element));
					entity.getCategories().add(newCategory);
				}
			}

		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		}
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nlList = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	public static void writeAIML(Entity entity) {
		try {
			FileWriter fw = new FileWriter(entity.getName().trim() + ".aiml");
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			fw.write("<aiml>\n");
			
			for(Category category:entity.getCategories()) {
				fw.write("\t<category>\n");
				fw.write("\t\t<pattern>"+category.getPattern().trim()+"</pattern>\n");
				fw.write("\t\t<template>"+category.getTemplate().trim()+"</template>\n");
				fw.write("\t</category>\n");
			}
			
			fw.write("</aiml>");
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
