package org.example.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.l;

@Service
public class EbankAiAgent {

    ChatClient chatClient;

    public EbankAiAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Tu es un assistant qui répond aux questions de l'utilisateur
                         sur les clients et les comptes bancaires, en fonction du contexte fourni.
                         Si aucun contexte n'est fourni, réponds : je ne sais pas.""")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory)
                        .build())
                .defaultAdvisors(a -> a.param(ChatMemory.CONVERSATION_ID, "default"))
                .defaultTools(tools)
                .build();
    }


    public String chat(Prompt prompt) {

        return chatClient.prompt(prompt).call().content();
    }

    public Flux<String> chatStream(Prompt prompt) {
        return chatClient.prompt(prompt).stream().content();
    }


}
