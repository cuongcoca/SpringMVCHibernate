package com.coca.controller;

import com.coca.dao.ProductDAO;
import com.coca.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @RequestMapping("/index.html")
    public String index(){
        return "products/index";
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity search(){
        List<Product> list = productDAO.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Product product){
        productDAO.add(product);
        return "products/index";
    }
}
