package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.Category;
import com.example.beautydiary.entities.PriceListItem;
import com.example.beautydiary.services.BeauticianService;
import com.example.beautydiary.services.CategoryService;
import com.example.beautydiary.services.MasterAccountService;
import com.example.beautydiary.services.UserService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class MasterAccountController {
    private MasterAccountService mas;
    private CategoryService categoryService;
    private BeauticianService beauticianService;
    private UserService userService;

    @Value("${profile.image.path}")
    private String profileImagePath;

    @Autowired
    public MasterAccountController(MasterAccountService mas, CategoryService categoryService,
                                   BeauticianService beauticianService, UserService userService) {
        this.mas = mas;
        this.categoryService = categoryService;
        this.beauticianService = beauticianService;
        this.userService = userService;
    }

    @GetMapping("/master-account/{beauticianId}")
    public String viewMasterAccountPage(@PathVariable("beauticianId") Long beauticianId, Model model,
                                        @CookieValue(value = "userId") String userIdFromCookie) {
        if (userIdFromCookie == null || !(userIdFromCookie.equals(beauticianId.toString()))) {
            return "redirect:/home";
        }
        Beautician beautician = beauticianService.getById(beauticianId);
        List<PriceListItem> itemList = mas.getAllByBeauticianId(beauticianId);
        PriceListItem item = new PriceListItem();
        item.setBeautician(beautician);
        List<Category> categories = categoryService.findAllCategories();
        Category category = new Category();
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        model.addAttribute("itemList", itemList);
        model.addAttribute("item", item);
        model.addAttribute("name", beautician.getFullName());
        model.addAttribute("be", beautician);
        return "/master-account";
    }

    @PostMapping("/master-account/{beauticianId}/addPriceList")
    public String addPriceListItem(@ModelAttribute("priceListItem") PriceListItem priceListItem,
                                   @PathVariable("beauticianId") Long beauticianId) {
        mas.addPriceListItem(priceListItem);
        return "redirect:/master-account/" + beauticianId;
    }

    @PostMapping("/master-account/{beauticianId}/updateProfile")
    public String updateProfile(@ModelAttribute("beautician") Beautician beautician,
                                @PathVariable("beauticianId") Long beauticianId) throws Exception {
        try {
            beautician.setId(beauticianId);
            userService.updateBeautician(beautician);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/master-account/" + beauticianId;
    }

    @PostMapping("/master-account/{beauticianId}/uploadImage")
    public String uploadImage(@PathVariable("beauticianId") Long beauticianId,
                              @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            String fileName = profileImagePath + beauticianId + ".jpg";
            mas.saveImage(imageFile, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/master-account/" + beauticianId;
    }


}

