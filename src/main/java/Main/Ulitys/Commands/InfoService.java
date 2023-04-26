package Main.Ulitys.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class InfoService extends ListenerAdapter {

    public void onSlashCommandInteraction (@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("service")) {

            String Type = event.getOption("type").getAsString();
            String Info = event.getOption("info").getAsString();

            EmbedBuilder lock = new EmbedBuilder()
                    .setColor(Color.BLUE)
                    .setTitle("Servivce-Info")
                    .addField("type", " " + Type, true)
                    .addField("info", " " + Info, true);

            event.replyEmbeds(lock.build()).setEphemeral(true).queue();

        }
    }
}
