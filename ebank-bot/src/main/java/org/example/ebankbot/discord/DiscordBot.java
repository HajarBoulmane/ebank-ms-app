package org.example.ebankbot.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.ebankbot.agents.EbankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;

@DiscordController
public class DiscordBot {
    private EbankAiAgent ebankAiAgent;

    public DiscordBot(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @DiscordMapping
    public void perform(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;            // éviter de traiter ses propres messages
        String query = event.getMessage().getContentRaw();
        String response = ebankAiAgent.chat(new Prompt(query));
        event.getChannel().sendMessage(response).queue();
    }



}
