package spring.in.action.fifth.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.in.action.fifth.tacocloud.Ingredient;
import spring.in.action.fifth.tacocloud.Order;
import spring.in.action.fifth.tacocloud.Taco;
import spring.in.action.fifth.tacocloud.User;
import spring.in.action.fifth.tacocloud.data.IngredientRepository;
import spring.in.action.fifth.tacocloud.data.TacoRepository;
import spring.in.action.fifth.tacocloud.data.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static spring.in.action.fifth.tacocloud.Ingredient.*;

@Slf4j
@Controller
@RequestMapping("design")
@SessionAttributes("order")
public class DesignTacoController {
    private static final String DESIGN_VIEW_NAME = "design";

    private final IngredientRepository ingredientRepository;
    private final TacoRepository designRepository;
    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.designRepository = designRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Arrays.stream(Type.values()).forEach(type ->
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return DESIGN_VIEW_NAME;
    }

    @PostMapping
    //有坑，在将Taco的ingredients的列表存储对象修改后，需新增Converter，否则一直报错，参考https://www.cnblogs.com/coder-qi/p/10706765.html
    public String processDesign(@Valid Taco designTaco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return DESIGN_VIEW_NAME;
        }

        Taco saved = designRepository.save(designTaco);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }
}
