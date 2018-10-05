package com.keycloakdemo.authserver.keycloak.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WebController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/products")
    public String products(Principal principal, Model model) {
    	Iterable<Product> products = productDAO.findAll();
    	if(products != null && products.iterator().hasNext()) {
    		System.out.println("products available for user");
    	} else {
    		addProducts();
    		products = productDAO.findAll();
    	}

        model.addAttribute("products", products);
        if(principal != null)
        	model.addAttribute("username", principal.getName());
        return "products";
    }

    // add products for demonstration
    public void addProducts() {

        Product p1 = new Product();
        
        p1.setName("DELL LAPTOP");
        p1.setPrice("70k");
        productDAO.save(p1);

        Product p2 = new Product();
       
        p2.setName("APPLE LAPTOP");
        p2.setPrice("175k");
        
        productDAO.save(p2);

        Product p3 = new Product();
        
        p3.setName("ACER LAPTOP");
        p3.setPrice("120K");
       
        productDAO.save(p3);
    }
}