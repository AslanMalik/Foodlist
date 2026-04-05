package kz.main.foodlist.controller;

import kz.main.foodlist.entity.Food;
import kz.main.foodlist.entity.Manufacturer;
import kz.main.foodlist.repository.CustomFoodRepository;
import kz.main.foodlist.repository.FoodRepository;
import kz.main.foodlist.repository.ManRepository;
import kz.main.foodlist.service.FoodService;
import kz.main.foodlist.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FoodController {

//    private final FoodRepository foodRepository;
//    private final ManRepository manRepository;
//    private final CustomFoodRepository customFoodRepository;


    @Autowired
    @Qualifier("TEST")
    private  FoodService foodService;
    private final ManufacturerService manufacturerService;

    @GetMapping(value = "/")
    public String getMain(Model model) {
        model.addAttribute("food", foodService.getFoodByAscending());
        model.addAttribute("man", manufacturerService.getAllManufacturer());
        return "index";
    }

    @PostMapping(value = "/add")
    public String addStudent(Food food) {
        foodService.addFood(food);
        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String updateFood(Food food) {
        foodService.updateFood(food);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteStudent(int id) {
        foodService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/poisk")
    public String getFood(Model model,
                          @RequestParam String search){
        model.addAttribute("food", foodService.getAllFoodByWord(search));
        return "index";
    }

    @GetMapping(value = "/more")
    public String getFoodByPriceMore(@RequestParam  Integer price, Model model) {
        model.addAttribute("food", foodService.getFoodByPriceMore(price));

        return "index";
    }

    @GetMapping(value = "/more-name")
    public String getFoodByPriceMore(@RequestParam(required = false)  Integer price, @RequestParam(required = false) String name, Model model) {
        model.addAttribute("food", foodService.getFoodByPriceAndName(price, name));

        return "index";
    }


    @GetMapping(value = "/sortPrice")
    public String getFoodByPriceMore(Model model) {
        model.addAttribute("food", foodService.getFoodBySort());

        return "index";
    }


    @GetMapping(value = "/pagination")
     public String getFoodByPagination(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "id") String param,
                                       @RequestParam(defaultValue = "asc") String direction,
                                       Model model) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(param).descending(): Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Food> foodPage = foodService.findAllPagination(pageable);

        model.addAttribute("food", foodPage.getContent());

        return "index";

    }

    @GetMapping(value = "/pagination-by-price")
    public String getFoodByPaginationByPrice(@RequestParam int price,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size,
                                             @RequestParam(defaultValue = "price") String param,
                                             @RequestParam(defaultValue = "desc") String direction,
                                             Model model) {
        Sort sort = direction.equalsIgnoreCase("desc")?
                Sort.by(param).descending(): Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Food> foodPage = foodService.findByPriceGreaterThan(price, pageable);

        model.addAttribute("food", foodPage.getContent());
        return "index";
    }







}
