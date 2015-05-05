package com.jsoupexample.sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OlympicRsultScrapper {
	public static final String OLYMPIC_DATABASE_URL = "http://www.databaseolympics.com/games/gamesyear.htm?g=47";

	public static void main(String[] args) throws IOException, SQLException,
			InterruptedException {
		Document doc = Jsoup.connect(OLYMPIC_DATABASE_URL).get();
		String title = doc.title();
		System.out.println(title);
		Element table = doc.select("table.pt8").get(0);
		Elements trs = table.select("tr");
		Iterator trIter = trs.iterator();
		boolean firstRow = true;
		while (trIter.hasNext()) {

			Element tr = (Element) trIter.next();
			if (firstRow) {
				firstRow = false;
				continue;
			}
			Elements tds = tr.select("td");
			Iterator tdIter = tds.iterator();
			int tdCount = 1;
			String country = null;
			Integer gold = null;
			Integer silver = null;
			Integer bronze = null;
			Integer total = null;
			// process new line
			while (tdIter.hasNext()) {

				Element td = (Element) tdIter.next();
				switch (tdCount++) {
				case 1:
					country = td.select("a").text();
					break;
				case 2:
					gold = Integer.parseInt(td.text());
					break;
				case 3:
					silver = Integer.parseInt(td.text());
					break;
				case 4:
					bronze = Integer.parseInt(td.text());
					break;
				case 5:
					total = Integer.parseInt(td.text());
					break;
				}

			}
			System.out.println(country + ": gold " + gold + " silver " + silver
					+ " bronze " + bronze + " total " + total);
		} // table rows
	}

}
