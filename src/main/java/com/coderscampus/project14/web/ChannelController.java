package com.coderscampus.project14.web;

import com.coderscampus.project14.domain.Message;
import com.coderscampus.project14.repository.MessageRepository;
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

    @GetMapping("/{channelId}")
    public String getChannel(@PathVariable String channelId, ModelMap model) {
        model.put("channelId", channelId);
        return "channel";
    }

    @GetMapping("/{channelId}/messages")
    @ResponseBody
    public List<Message> getMessages(@PathVariable String channelId) {
        return messageRepo.findByChannel(channelId);
    }

    @PostMapping("/{channelId}/messages")
    @ResponseBody
    public Message postMessage(@PathVariable String channelId,
                               @RequestParam String content,
                               @RequestParam String sender) {
        Message newMessage = new Message(content, sender, channelId);
        return messageRepo.save(newMessage);
    }
}
