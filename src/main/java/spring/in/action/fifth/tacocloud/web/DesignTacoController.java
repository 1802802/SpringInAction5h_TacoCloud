package spring.in.action.fifth.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.in.action.fifth.tacocloud.Ingredient;
import spring.in.action.fifth.tacocloud.Taco;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static spring.in.action.fifth.tacocloud.Ingredient.*;

@Slf4j
@Controller
@RequestMapping("design")
public class DesignTacoController {
    private static final String DESIGN_VIEW_NAME = "design";

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        for (Type type : Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute(DESIGN_VIEW_NAME, new Taco());
        return DESIGN_VIEW_NAME;
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute(DESIGN_VIEW_NAME) Taco designTaco, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return DESIGN_VIEW_NAME;
        }

        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + designTaco);
        log.info("Model: " + model);

        return "redirect:/orders/current";
    }
}
