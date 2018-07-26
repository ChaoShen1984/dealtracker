package com.chao.project.dealtracker.item.comparator;

import java.util.Comparator;

import com.chao.project.dealtracker.item.Item;

public class ItemPriceComparator implements Comparator<Item>  {
	
	private NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator();
	public int compare(Item o1, Item o2) {
		return naturalOrderComparator.compare(o1.getPrice(), o2.getPrice());
	}
}
