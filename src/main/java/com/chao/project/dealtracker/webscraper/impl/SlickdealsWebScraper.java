package com.chao.project.dealtracker.webscraper.impl;

import com.chao.project.dealtracker.item.Item;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

/**
 * Example from https://ksah.in/introduction-to-web-scraping-with-java/.
 * 
 * @author chao shen
 *
 */
public class SlickdealsWebScraper extends BaseWebScraper {

	@Override
	public Item createItemBasedOnElement(HtmlElement htmlItem) {
		HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//a[@class='dealTitle']"));
		HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//div/span[@class='price']"));

		// It is possible that an item doesn't have any price, we set the price to 0.0
		// in this case
		String itemPrice = spanPrice == null ? "$0.0" : spanPrice.asText();

		Item item = new Item();
		item.setTitle(itemAnchor.asText());
		item.setUrl(getBaseURL() + itemAnchor.getHrefAttribute());
		item.setPrice(itemPrice);
		return item;
	}

	@Override
	protected String getBaseURL() {
		return "https://slickdeals.net";
	}

	@Override
	protected String getSearchPath() {
		return "/newsearch.php?src=SearchBarV2&q=";
	}

	@Override
	protected String getPagePath() {
		return "&page=";
	}

	@Override
	protected String getElementPath() {
		return "//div[@class='resultRow']";
	}
}
