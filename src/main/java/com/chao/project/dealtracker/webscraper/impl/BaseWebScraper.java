package com.chao.project.dealtracker.webscraper.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.chao.project.dealtracker.item.Item;
import com.chao.project.dealtracker.webscraper.WebScraper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class BaseWebScraper implements WebScraper {

	public List<Item> getItems(String searchWords, String pageNumber) {
		List<Item> myItems = new ArrayList<Item>();
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			String searchUrl = getSearchPath(searchWords, pageNumber);
			HtmlPage page = client.getPage(searchUrl);

			@SuppressWarnings("unchecked")
			List<HtmlElement> items = (List<HtmlElement>) page.getByXPath(getElementPath());
			if (items.isEmpty()) {
				System.out.println("No items found !");
			} else {
				for (HtmlElement htmlItem : items) {
					myItems.add(createItemBasedOnElement(htmlItem));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.close();
		return myItems;
	}

	protected abstract Item createItemBasedOnElement(HtmlElement htmlItem);

	protected abstract String getElementPath();

	protected abstract String getBaseURL();

	protected abstract String getSearchPath();

	protected abstract String getPagePath();

	private String getSearchPath(String searchWords, String pageNumber) {
		try {
			return getBaseURL() + getSearchPath() + URLEncoder.encode(searchWords, "UTF-8") + getPagePath()
					+ pageNumber;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
