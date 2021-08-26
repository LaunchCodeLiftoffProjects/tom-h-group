package com.fizzybubbly.fizzybubbly.controllers;

import com.fizzybubbly.fizzybubbly.models.Drink;
import com.fizzybubbly.fizzybubbly.models.DrinkData;
import com.fizzybubbly.fizzybubbly.models.data.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping ("productPage")
public class ProductPageController {

    @Autowired
    private DrinkRepository drinkRepository;

    @RequestMapping("")
    public String productInfo(Model model) {
        model.addAttribute("title", "seltzy info");
        model.addAttribute("drinks", drinkRepository.findAll());
        return "productPage";
    }

    @GetMapping("productPage")
    public String displayDrinkDetails(@RequestParam Integer drinkId, Model model) {
        Optional<Drink> result = drinkRepository.findById(drinkId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Drink ID: " + drinkId);
        } else {
            Drink drink = result.get();
            model.addAttribute("title", drink.toString() + " Details");
            model.addAttribute("drink", drink);
                    }
        return "productPage";
    }

}
