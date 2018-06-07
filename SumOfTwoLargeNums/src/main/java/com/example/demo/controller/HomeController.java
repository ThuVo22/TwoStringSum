package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.TwoStringsSum;

@Controller
public class HomeController {
    @GetMapping("/")
    public  String home(Model model) {
        return "index";
    }
    
    @PostMapping("/")
    public @ResponseBody String result(Model model,@RequestParam("numberA") String numberA, @RequestParam("numberB") String numberB) {
       
        String result = TwoStringsSum.findSum(numberA, numberB);
        model.addAttribute("numberA", numberA);
        model.addAttribute("numberB", numberB);
       // model.addAttribute("result", result);        
        //return "index";
        return result;
    }
}   