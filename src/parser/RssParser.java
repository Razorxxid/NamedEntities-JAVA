package parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import feed.Feed;
import feed.Article;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm 
 * */

public class RssParser<Item> extends GeneralParser {

	public RssParser(String fname) {
		super(fname);
	}


	public Feed feedParser() {
		Feed feed = new Feed(this.filename);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			/* Lee el archivo XML y lo guarda en doc */
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(this.getFilename());
			NodeList itemList = doc.getElementsByTagName("item");
  
			for (int i = 0; i < itemList.getLength(); i++) {
				/* Obtiene los datos del archivo XML */
				Node node = itemList.item(i);
				Element element = (Element) node;
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String link = element.getElementsByTagName("link").item(0).getTextContent();
				String description = element.getElementsByTagName("description").item(0).getTextContent();

				/*Fetchea pubDate como String y la convierte al formato Date  */
				String pubDateStr = element.getElementsByTagName("pubDate").item(0).getTextContent();
				SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
				Date pubDate;
				try {
					pubDate = formatter.parse(pubDateStr);
					/* Crea el articulo con los datos obtenidos y lo agrega al feed */
					Article art = new Article(title, description, pubDate, link);
					feed.addArticle(art);
				} catch (ParseException e) {
					e.printStackTrace();
				}		
			}
			return feed;

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
