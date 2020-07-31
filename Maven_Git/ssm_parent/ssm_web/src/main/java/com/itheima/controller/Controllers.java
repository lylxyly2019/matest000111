package com.itheima.controller;

import com.itheima.pojo.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class Controllers {
    @Autowired
    private ItemsService itemsService;
@RequestMapping("/save")
    public String list(Model model){
    List<Items> all = itemsService.findAll();
    model.addAttribute("items",all);
    return "items";
}
}
