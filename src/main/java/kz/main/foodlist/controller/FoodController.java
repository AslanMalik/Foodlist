package kz.main.foodlist.controller;

import kz.main.foodlist.entity.Food;
import kz.main.foodlist.entity.Manufacturer;
import kz.main.foodlist.repository.FoodRepository;
import kz.main.foodlist.repository.ManRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodRepository foodRepository;
    private final ManRepository manRepository;

    @GetMapping(value = "/")
    public String getMain(Model model) {
        model.addAttribute("food", foodRepository.findAll());
        model.addAttribute("man", manRepository.findAll());
        return "index";
    }

    @PostMapping(value = "/add")
    public String addStudent(Food food) {
        foodRepository.save(food);
        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String updateFood(Food food) {
        foodRepository.save(food);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteStudent(int id) {
        foodRepository.deleteById(id);
        return "redirect:/";
    }

}
