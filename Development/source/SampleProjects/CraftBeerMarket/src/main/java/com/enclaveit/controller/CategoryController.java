package com.enclaveit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.enclaveit.model.Beer;
import com.enclaveit.model.Category;
import com.enclaveit.service.BeerService;
import com.enclaveit.service.CategoryService;

/**
 * @author Ace
 *
 */
@Controller
public class CategoryController {

    private CategoryService categoryService;
    private BeerService beerService;
    
    @Autowired(required=true)
    @Qualifier(value="categoryService")
    public void setCategoryService(CategoryService cs){
        this.categoryService = cs;
    }
    
    @Autowired(required=true)
    @Qualifier(value="beerService")
    public void setBeerService(BeerService bs){
        this.beerService = bs;
    }
    
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String listCategories(@ModelAttribute("Category") Category category, Model model, Integer offset, Integer maxResults) {
        model.addAttribute("category", new Category());
        model.addAttribute("listCategories", this.categoryService.listCategory(offset, maxResults));
        model.addAttribute("count", this.categoryService.count());
        model.addAttribute("offset", offset);
        return "category";
    }
    
    // Category Zone
    //For add and update category both
    @RequestMapping(value= "/category/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("Category") Category category, Integer offset, Integer maxResults){
        ModelAndView modelAndView = new ModelAndView();
        if(category.getId() == 0){
            //new category, add it, check exist name of category
            if(this.categoryService.addCategory(category)){
                modelAndView.addObject("errorMessage","Category name {"+ category.getName() +"} already exists. Please choose another name");
                modelAndView.addObject("category", category);
            }else{
                modelAndView.addObject("successMessage","Category name {" + category.getName() + "} added successfully");
                modelAndView.addObject("category", new Category());
            }
        }else{
            //existing category, call update
            this.categoryService.updateCategory(category);
            modelAndView.addObject("category", new Category());
            modelAndView.addObject("updateSuccess","Category name {" + category.getName() + "} updated successfully");
        }
        modelAndView.addObject("listCategories", this.categoryService.listCategory(offset,maxResults));
        modelAndView.addObject("count", this.categoryService.count());
        modelAndView.setViewName("category");
        return modelAndView;
    }
 
    @RequestMapping("/remove/{id}")
    public String removeCategory(@PathVariable("id") int id){
        this.categoryService.removeCategory(id);
        return "redirect:/category";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model, Integer offset, Integer maxResults){
        model.addAttribute("category", this.categoryService.getCategoryById(id));
        model.addAttribute("listCategories", this.categoryService.listCategory(offset, maxResults));
        model.addAttribute("count", this.categoryService.count());
        return "category";
    }
    
    //Beer Zone
    @RequestMapping(value = "/beer", method = RequestMethod.GET)
    public String listBeers(Model model, Integer offset, Integer maxResults) {
        model.addAttribute("beer", new Beer());
        model.addAttribute("listCategories", this.categoryService.listCategory(offset, maxResults));
        model.addAttribute("listBeers", this.beerService.listBeerNotArc());
        model.addAttribute("count", this.categoryService.count());
        return "beer";
    }
    
    //For add and update beer both
    @RequestMapping(value= "/beer/add", method = RequestMethod.POST)
    public String addBeer(@ModelAttribute("beer") Beer beer, HttpServletRequest request){
        int category_id = Integer.valueOf(request.getParameter("categoryId"));
        Boolean archived = Boolean.parseBoolean(request.getParameter("archived"));
        beer.setCategoryId(category_id);
        beer.setArchived(archived);
        
        if(beer.getId() == 0){
            //new beer, add it
            this.beerService.addBeer(beer);
        }else{
            //existing beer, call update
            this.beerService.updateBeer(beer);
        }
         
        return "redirect:/beer";
    }
    
    // remove Beer
    @RequestMapping("/removeBeer/{category_id}/{id}")
    public String removeBeer(@PathVariable("category_id") int category_id, @PathVariable("id") int id){
        Beer getBeer = (Beer) this.beerService.getBeerById(id);
        if(getBeer.getId() != 0){
            this.beerService.removeBeer(id);
        }
        return "redirect:/beer";
    }
    
    // Edit Beer
    @RequestMapping("/editBeer/{category_id}/{id}")
    public String editBeer(@PathVariable("category_id") int category_id, @PathVariable("id") int id, Model model, Integer offset, Integer maxResults){
        model.addAttribute("beer", this.beerService.getBeerById(id));
        model.addAttribute("listCategories", this.categoryService.listCategory(offset, maxResults));
        model.addAttribute("listBeers", this.beerService.listBeerNotArc());
        return "beer";
    }
    
    // update archived not use ajax
    @RequestMapping("/archivedBeer/{category_id}/{id}/{archived}")
    public String archived(@ModelAttribute("beer") Beer beer, @PathVariable("category_id") int category_id, @PathVariable("id") int id, @PathVariable("archived") Boolean archived,HttpServletRequest request, Model model, Integer offset, Integer maxResults){
        if(archived == false){
            beer.setCategoryId(category_id);
            beer.setArchived(true);
            beer.setId(id);
        }else if(archived == true){
            beer.setCategoryId(category_id);
            beer.setArchived(false);
            beer.setId(id);
        }
        //existing beer, call update
        this.beerService.updateBeerArchived(beer.getId(), beer.getCategoryId(), beer.getArchived());
        model.addAttribute("beer", new Beer());
        model.addAttribute("listCategories", this.categoryService.listCategory(offset, maxResults));
        model.addAttribute("listBeers", this.beerService.listBeerNotArc());
        return "beer";
    }
}
