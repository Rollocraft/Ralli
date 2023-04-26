package Main.Ulitys.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class help extends ListenerAdapter {
    public void onSlashCommandInteraction (@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("help")) {

            EmbedBuilder lock = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setTitle("Service-Info")
                    .addField("/register", "Registers ur Team" , true)
                    .addField("/service", "Gets u to a Service" , true);

            event.replyEmbeds(lock.build()).setEphemeral(true).queue();

        }
    }
}
