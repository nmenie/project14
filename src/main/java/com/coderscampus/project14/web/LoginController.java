package com.coderscampus.project14.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin(ModelMap model) {
        model.put("username", ""); // Add empty username to the model
        return "login"; // Return the login view
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam String username, HttpSession session, ModelMap model) {
        session.setAttribute("username", username); // Store username in session
        model.put("username", username); // Store username in model for immediate access
        return "redirect:/channels"; // Redirect to channels page after login
    }

    @GetMapping("/channels")
    public String getChannels(HttpSession session, ModelMap model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Redirect to login if no username is found in session
        }
        model.put("username", username); // Store username in model for display
        return "channels"; // Return the channels view
    }
}


