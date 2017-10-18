
package com.fabHotels.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlReader {

	static private Document doc ; 
	
	public HtmlReader(String content){
		doc = Jsoup.parse(content) ;
	}
	
	public String getElementByTagName(String TagName){
		Elements el = doc.select(TagName);
		if(el.size()>=1){
			return el.text();
		}else
			return null;
	}
}
	
