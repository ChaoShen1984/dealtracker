package com.chao.project.dealtracker.webscraper.impl;

import com.chao.project.dealtracker.item.Item;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

public class DealMoonWebScraper extends BaseWebScraper {

	@Override
	public Item createItemBasedOnElement(HtmlElement htmlItem) {
		HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem
				.getFirstByXPath(".//div[@class='mtxt clearfix']//a[@class='event_statics_action']"));
		HtmlElement spanPrice = ((HtmlElement) itemAnchor.getFirstByXPath(".//span[@class='notice_item']"));
		HtmlElement itemTitle = (HtmlElement) spanPrice.getNextElementSibling();

		// It is possible that an item doesn't have any price, we set the price to 0.0
		// in this case
		String itemPrice = spanPrice == null ? "$0.0" : spanPrice.asText();

		Item item = new Item();
		item.setTitle(itemTitle.asText());
		item.setUrl(itemAnchor.getHrefAttribute());
		item.setPrice(itemPrice);
		return item;
	}

	@Override
	protected String getBaseURL() {
		return "https://www.dealmoon.com";
	}

	@Override
	protected String getSearchPath() {
		return "/top/";
	}

	@Override
	protected String getPagePath() {
		return "?p=";
	}

	@Override
	protected String getElementPath() {
		return "//div[@class='mlist']";
	}
}
