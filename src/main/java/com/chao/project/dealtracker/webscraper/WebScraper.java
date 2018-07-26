package com.chao.project.dealtracker.webscraper;

import java.util.List;

import com.chao.project.dealtracker.item.Item;

/**
 * Example from https://ksah.in/introduction-to-web-scraping-with-java/.
 * 
 * @author chao shen
 *
 */
public interface WebScraper {
	public List<Item> getItems(String searchWords, String page);
}
