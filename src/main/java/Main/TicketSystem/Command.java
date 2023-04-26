package Main.TicketSystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Objects;

public class Command extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().equals("!setup Ticket")) {

            System.out.println("setup 1");

            if (Objects.requireNonNull(event.getMember()).getPermissions().contains(Permission.ADMINISTRATOR)) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("**     Tickets      **");
                embed.setColor(Color.YELLOW);
                embed.setDescription("");
                embed.addField("If you have any ...", "Questions, Bug-Reports, Hacking-applies", true);
                embed.addField("", "⬇️Create a Ticket Here⬇️" , false);



                Button button = Button.success("openTicket", "Create Ticket");

                event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(button).queue();

            }
        }
    }
}
