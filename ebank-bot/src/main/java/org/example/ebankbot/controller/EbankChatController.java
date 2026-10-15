package org.example.ebankbot.controller;

import org.example.ebankbot.agents.EbankAiAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbankChatController {

    private EbankAiAgent ebankAiAgent;

    public EbankChatController(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }
    @GetMapping(value = "/chat")
    public String chat(@RequestParam(defaultValue = "bonjour") String query) {
        return ebankAiAgent.chat(new Prompt(query));
    }

}
