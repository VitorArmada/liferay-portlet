package pt.impresa.liferay.content.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import pt.impresa.log.ILog;

public class ContentOpenSearchUtils {
	
	public static final int DEFAULT_NAMESPACE = 0;

	public static final int LIFERAY_NAMESPACE = 4;

	public static final int NO_NAMESPACE = 3;

	public static final int OS_NAMESPACE = 1;

	public static final int RELEVANCE_NAMESPACE = 2;
	
	public static final String DEFAULT_NS = "xmlns";
	
	public static final String LIFERAY_NS = "liferay";
	
	public static final String OS_NS = "opensearch";
	
	public static final String RELEVANCE_NS = "relevance";
	
	
	public static Element createNamespace(Document doc, String name){
		return doc.createElementNS("http://www.w3.org/2005/Atom", name);
	}
	
	public static void setNamespace(int namespaceType, Element element) {
		if (namespaceType == DEFAULT_NAMESPACE) {
			element.setAttribute(DEFAULT_NS, "http://www.w3.org/2005/Atom");
		}
		else if (namespaceType == LIFERAY_NAMESPACE) {
			element.setAttribute(LIFERAY_NS, "http://liferay.com/spec/liferay-search/1.0/");
		}
		else if (namespaceType == OS_NAMESPACE) {
			element.setAttribute(DEFAULT_NS+":"+OS_NS, "http://a9.com/-/spec/opensearch/1.1/");
		}
		else if (namespaceType == RELEVANCE_NAMESPACE) {
			element.setAttribute(RELEVANCE_NS, "http://a9.com/-/opensearch/extensions/relevance/1.0/");
		}
	}
	
	public static void addElement(Document doc, Element root, String name, String value, int namespaceType){
		if (namespaceType == NO_NAMESPACE) {
			Element element = doc.createElement(name);
			element.appendChild(doc.createTextNode(value));
			root.appendChild(element);
		}else if (namespaceType == OS_NAMESPACE) {
			Element element = doc.createElement(OS_NS+":"+name);
			element.appendChild(doc.createTextNode(value));
			root.appendChild(element);
		}
	}
	
	public static Element createElement(Document doc, Element root, String name, int namespaceType){
		Element result = null;	
		if (namespaceType == NO_NAMESPACE) {
			result = doc.createElement(name);
			root.appendChild(result);		
		}else if (namespaceType == OS_NAMESPACE) {
			result = doc.createElement(OS_NS+":"+name);
			root.appendChild(result);
		}	
		return result;
	}

	public static String getStringFromDoc(Document doc) {
		DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
	    LSSerializer lsSerializer = domImplementation.createLSSerializer();
	    return lsSerializer.writeToString(doc);   
	}
	
	public static String getEntriesHtml(String className, String href, String value){
		String result = null;
		result = "<a class=\""+className+"\" href=\"";
		result += href;
		result += "\">";
		result += createImgHtml(value);
		result += "</a>";		
		return result;
	}
	
	private static String createImgHtml(String value){
		return ("<img src="+value+"\">");
	}
	
	public static String getSearchDateFormat(String sDate){
		String result = null;
		Locale locale = new Locale("pt", "PT");
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate) ;
			result = new SimpleDateFormat("dd 'de' MMMMM yyyy", locale).format(date);
		} catch (ParseException e) {
			ILog.get().error(e);
		}
		return result;
	}
	
	public static String getPublishedTimeFromDateTime(String dateTime){
		if(dateTime!=null){
			int tIndex = dateTime.indexOf('T');
			return (tIndex > 0) ? dateTime.substring(tIndex+1, tIndex+6) : null;
		}
		return null;
	}
}
