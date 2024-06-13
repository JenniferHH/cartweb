package com.example.cartapp;

import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;


@Controller
class CartController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        return "home";
    }

    @GetMapping("/bread")
    public String bread(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        return "bread";
    }

    @GetMapping("/chocolatecake")
    public String chocolatecake(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        return "chocolatecake";
    }

    @GetMapping("/mooncake")
    public String mooncake(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        return "mooncake";
    }

    @GetMapping("/sweetbread")
    public String sweetbread(HttpSession session, Model model){
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        return "sweetbread";
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        double totalAmount = 0.0;
        for (CartItem item : cartItems) {
            totalAmount += item.getQuality() * item.getPrice();
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String formattedTotalAmount = df.format(totalAmount);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", formattedTotalAmount);

        return "cart";
    }


    @PostMapping ("/add")
    public String add(@RequestParam String name, @RequestParam int quality, @RequestParam double price, @NotNull HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }

        CartItem newItem = new CartItem(name, quality, price);
        cartItems.add(newItem);

        model.addAttribute("cartItems", cartItems);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("index") int index, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems != null && index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
            session.setAttribute("cartItems", cartItems);
        }
        return "redirect:/cart";
    }








}
