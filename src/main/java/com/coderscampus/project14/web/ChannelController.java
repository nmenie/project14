package com.coderscampus.project14.web;

import com.coderscampus.project14.domain.Message;
import com.coderscampus.project14.repository.MessageRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private MessageRepository messageRepo;

    // Show the chat room page for a specific channel
    @GetMapping("/{channelId}")
    public String getChannel(@PathVariable String channelId, HttpSession session, ModelMap model) {
        String username = (String) session.getAttribute("username");

        if (username == null || username.isEmpty()) {
            return "redirect:/login"; // Make sure user is logged in
        }

        model.put("username", username);
        model.put("channelId", channelId);
        return "channel"; // Render the channel.html page
    }

    // Fetch all messages for the given channel (used for polling)
    @GetMapping("/{channelId}/messages")
    @ResponseBody
    public List<Message> getMessages(@PathVariable String channelId) {
        return messageRepo.findByChannel(channelId);
    }

    // Post a new message to the channel
    @PostMapping("/{channelId}/messages")
    @ResponseBody
    public Message postMessage(@PathVariable String channelId,
                               @RequestParam String content,
                               HttpSession session) {

        String sender = (String) session.getAttribute("username");
        if (sender == null || sender.isEmpty()) {
            sender = "Guest";
        }

        Message newMessage = new Message(content, sender, channelId);
        return messageRepo.save(newMessage); // Save and return the message as JSON
    }
}