package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.Category;
import com.example.beautydiary.entities.PriceListItem;

import com.example.beautydiary.services.BeauticianService;
import com.example.beautydiary.services.CategoryService;
import com.example.beautydiary.services.MasterAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MasterAccountController {

    private MasterAccountService mas;
    private CategoryService categoryService;
    private BeauticianService beauticianService;

    @Autowired
    public MasterAccountController(MasterAccountService mas, CategoryService categoryService, BeauticianService beauticianService) {
        this.mas = mas;
        this.categoryService = categoryService;
        this.beauticianService = beauticianService;
    }

    @GetMapping("/create-master-account")
    public String showCreateMasterAccountPage(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "/create-master-account";
    }

   @GetMapping("/master-account/{beauticianId}")
    public String showMasterAccountPage(@PathVariable("beauticianId") Long beauticianId, Model model) {
        Beautician beautician = beauticianService.getById(beauticianId);
        List<PriceListItem> itemList = mas.getAllByBeauticianId(beauticianId);
        PriceListItem item = new PriceListItem();
        item.setBeautician(beautician);
        model.addAttribute("itemList", itemList);
        model.addAttribute("item", item);
        return "/master-account";
    }

    @PostMapping("/master-account/{beauticianId}")
    public String addPriceListItem(@ModelAttribute("priceListItem") PriceListItem priceListItem,
    @PathVariable("beauticianId") Long beauticianId) {
        mas.addPriceListItem(priceListItem);
        return "redirect:/master-account/" + beauticianId;
    }
}

