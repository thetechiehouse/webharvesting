package com.jsoupexample.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiScraper {
	public static void main(String[] args) {
		scrapeTopic("/wiki/Digital_marketing");
	}

	public static void scrapeTopic(String url) {
		String html = getUrl("http://www.wikipedia.org/" + url);
		Document doc = Jsoup.parse(html);
		
		// Select the <p> Elements from the document    
		Elements paragraphs = doc.select("p");

		// For each selected <p> element, print out its text
		for (Element e : paragraphs) {
			printLines(e.text());
		}
		
	//	String contentText = doc.select("#mw-content-text >p").first().text();
		//printLines(contentText);
	}

	public static String getUrl(String url) {
		URL urlObj = null;
		try {
			urlObj = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("The url was malformed!");
			return "";
		}
		URLConnection urlCon = null;
		BufferedReader in = null;
		String outputText = "";
		try {
			urlCon = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				outputText += line;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("There was an error connecting to the URL");
			return "";
		}
		return outputText;
	}
	
	public static void printLines(String content){
		String input[] = content.split("(?<=\\G.{150})");
        for(String str:  input){    
		System.out.println(str); 
        } 
	}
}
