package me.cockx.learn.recipestore.bootstrap;

import lombok.extern.slf4j.Slf4j;
import me.cockx.learn.recipestore.domain.*;
import me.cockx.learn.recipestore.repositories.CategoryRepository;
import me.cockx.learn.recipestore.repositories.RecipeRepository;
import me.cockx.learn.recipestore.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class recipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public recipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> unitEach = unitOfMeasureRepository.findByUom("Each");
        if(!unitEach.isPresent()){throw new RuntimeException("Unit Each not found");}

        Optional<UnitOfMeasure> unitTeaspoon = unitOfMeasureRepository.findByUom("Teaspoon");
        if(!unitTeaspoon.isPresent()){throw new RuntimeException("Unit Teaspoon not found");}

        Optional<UnitOfMeasure> unitTablespoon = unitOfMeasureRepository.findByUom("Tablespoon");
        if(!unitTablespoon.isPresent()){throw new RuntimeException("Unit Tablespoon not found");}

        Optional<UnitOfMeasure> unitCup = unitOfMeasureRepository.findByUom("Cup");
        if(!unitCup.isPresent()){throw new RuntimeException("Unit Cup not found");}

        Optional<UnitOfMeasure> unitPinch = unitOfMeasureRepository.findByUom("Pinch");
        if(!unitPinch.isPresent()){throw new RuntimeException("Unit Pinch not found");}

        Optional<UnitOfMeasure> unitOunce = unitOfMeasureRepository.findByUom("Ounce");
        if(!unitOunce.isPresent()){throw new RuntimeException("Unit Ounce not found");}

        Optional<UnitOfMeasure> unitDash = unitOfMeasureRepository.findByUom("Dash");
        if(!unitDash.isPresent()){throw new RuntimeException("Unit Dash not found");}

        Optional<UnitOfMeasure> unitPint = unitOfMeasureRepository.findByUom("Pint");
        if(!unitPint.isPresent()){throw new RuntimeException("Unit Pint not found");}

        UnitOfMeasure uEach = unitEach.get();
        UnitOfMeasure uTeaspoon = unitTeaspoon.get();
        UnitOfMeasure uTablespoon = unitTablespoon.get();
        UnitOfMeasure uCup = unitCup.get();
        UnitOfMeasure uPinch = unitPinch.get();
        UnitOfMeasure uOunce = unitOunce.get();
        UnitOfMeasure uDash = unitDash.get();
        UnitOfMeasure uPint = unitPint.get();

        log.warn("Creating sample units of measure");



        Optional<Category> catAmerican = categoryRepository.findByDescription("American");
        if(!catAmerican.isPresent()){throw new RuntimeException("Category American not found");}

        Optional<Category> catItalian = categoryRepository.findByDescription("Italian");
        if(!catItalian.isPresent()){throw new RuntimeException("Category Italian not found");}

        Optional<Category> catMexican = categoryRepository.findByDescription("Mexican");
        if(!catMexican.isPresent()){throw new RuntimeException("Category Mexican not found");}

        Optional<Category> catFastFood = categoryRepository.findByDescription("Fast-Food");
        if(!catFastFood.isPresent()){throw new RuntimeException("Category Fast-Food not found");}

        Category cAmerican = catAmerican.get();
        Category cItalian = catItalian.get();
        Category cMexican = catMexican.get();
        Category cFastFood = catFastFood.get();

        log.warn("Creating sample categories");


//        Recipe for guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), uEach));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), uTeaspoon));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), uTablespoon));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), uTablespoon));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), uEach));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), uTablespoon));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), uDash));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), uEach));

        guacRecipe.addCategory(cAmerican);
        guacRecipe.addCategory(cMexican);

        recipes.add(guacRecipe);


        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), uTablespoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), uTeaspoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), uTeaspoon));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), uTeaspoon));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), uTeaspoon));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Chopped", new BigDecimal(1), uEach));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), uTablespoon));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), uTablespoon));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), uTablespoon));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), uTablespoon));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), uEach));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), uCup));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), uEach));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), uEach));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), uPint));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), uEach));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), uEach));
        tacosRecipe.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(4), uCup));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), uEach));

        tacosRecipe.addCategory(cAmerican);
        tacosRecipe.addCategory(cMexican);

        recipes.add(tacosRecipe);

        log.warn("Creating sample recipes");

        return recipes;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }
}
