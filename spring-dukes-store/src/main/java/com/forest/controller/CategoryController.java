package com.forest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forest.controller.support.MessageHelper;
import com.forest.entity.Category;
import com.forest.service.ICategoryService;

@Controller
public class CategoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ICategoryService						categoryService;
	
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("items", categoryService.getItems());
        return "product/listCategory";
    }
	
	@RequestMapping(value = "/admin/category/list", method = RequestMethod.GET)
    public String adminCategoryList(Model model) {
        model.addAttribute("items", categoryService.getItems());
        model.addAttribute("pagination", categoryService.getPagination());
        return "/admin/category/list";
    }
	
	@RequestMapping(value = "admin/category/previous", method = RequestMethod.GET)
    public String adminCategoryPrevious(Model model) {
		if (categoryService.getPagination().isHasPreviousPage()){
			categoryService.getPagination().previousPage();
		}else{
			LOGGER.warn("Pagination has no previous page. Wrong UI logic!");
		}
		model.addAttribute("items", categoryService.getItems());
        model.addAttribute("pagination", categoryService.getPagination());
        return "/admin/category/list";
    }
	
	@RequestMapping(value = "admin/category/next", method = RequestMethod.GET)
    public String adminCategoryNext(Model model) {
		if (categoryService.getPagination().isHasNextPage()){
			categoryService.getPagination().nextPage();
		}else{
			LOGGER.warn("Pagination has no next page. Wrong UI logic!");
		}
		model.addAttribute("items", categoryService.getItems());
        model.addAttribute("pagination", categoryService.getPagination());
        return "/admin/category/list";
    }
	
		
	@RequestMapping(value = "/admin/category/{id}/view", method = RequestMethod.GET)
    public String adminCategoryView(@PathVariable("id") int id, Model model) {
		Category category = categoryService.findById(id);
        model.addAttribute(category);
        return "/admin/category/view";
    }
	
	@RequestMapping(value = "admin/category/{id}/edit", method = RequestMethod.GET)
    public String adminCategoryEdit(@PathVariable("id") int id, Model model) {
		Category category = categoryService.findById(id);
        model.addAttribute(category);
        return "/admin/category/edit";
    }
	
	@RequestMapping(value = "admin/category/delete", method = RequestMethod.DELETE)
    public String adminCategoryDelete(@RequestParam("id") Integer id, Model model) {
		Category category = categoryService.findById(id);
        model.addAttribute(category);
        return "category/edit";
    }
	
	@RequestMapping(value = "/admin/category/create", method = RequestMethod.GET)
    public String adminCategoryCreate(Model model) {
        model.addAttribute(new Category());
        return "/admin/category/create";
    }
	
	@RequestMapping(value = "/admin/category/create", method = RequestMethod.POST)
    public String adminCategoryCreatePost(@Valid Category category, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) {
            return "/admin/category/create";
        } else {
        	category = categoryService.save(category);
        	
        	MessageHelper.addSuccessAttribute(ra, "CategoryCreated");
            return "redirect:/admin/category/create";
        }
    }
	
	
	@RequestMapping(value = "/admin/category/prepareList", method = RequestMethod.GET)
    public String adminCategoryPrepareList(Model model) {
        model.addAttribute("items", categoryService.getItems());
        model.addAttribute("pagination", categoryService.getPagination());
        return "/admin/category/list";
    }
}
