package com.chao.project.dealtracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chao.project.dealtracker.item.Item;
import com.chao.project.dealtracker.item.comparator.ItemPriceComparator;
import com.chao.project.dealtracker.webscraper.impl.DealMoonWebScraper;
import com.chao.project.dealtracker.webscraper.impl.SlickdealsWebScraper;

@Controller
public class DealTrackerController {

	@GetMapping("/dealtracker")
	public String greeting(@RequestParam("searchWords") String searchWords, @RequestParam(name="pageNumber", defaultValue="1") String pageNumber,
			Model model) {
		List<Item> items = new ArrayList<Item>();
		items.addAll(new DealMoonWebScraper().getItems(searchWords, pageNumber));
		items.addAll(new SlickdealsWebScraper().getItems(searchWords, pageNumber));
		Collections.sort(items, new ItemPriceComparator());
		model.addAttribute("items", items);
		model.addAttribute("searchWords", searchWords);
		model.addAttribute("pageNumber", pageNumber);
		return "dealtracker";
	}
}
