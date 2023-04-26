package Main.TicketSystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;

public class ButtonClickEvent extends ListenerAdapter {

    public void onButtonInteraction(ButtonInteractionEvent event) {
        event.deferEdit().queue();
        if (event.getButton().getId().equals("openTicket")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (!roles.contains("Ticket")) {
                int min = 1000;
                int max = 9999;
                int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                Date date = new Date();
                Guild guild = event.getGuild();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.YELLOW);
                embed.setTitle(event.getUser().getName() + "´s Ticket");
                embed.setDescription("A Staff member will respond soon!");
                guild.createTextChannel(event.getMember().getUser().getName()+ "`s Ticket", guild.getCategoryById(""))
                        .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                        .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton(), claimButton()).queue();
                EmbedBuilder embedTeam = new EmbedBuilder();
                embedTeam.setColor(Color.GREEN);
                embedTeam.setTitle("Ticket System");
                embedTeam.addField("Opened by", event.getMember().getAsMention(), true);
                embedTeam.addField("Datum", formatter.format(date), true);
                guild.getTextChannelById("").sendMessageEmbeds(embedTeam.build()).queue();
            }

        } else if (event.getButton().getId().equals("closeButton")) {
            String roles = String.valueOf(event.getMember().getRoles());
            if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                EmbedBuilder embedDelete = new EmbedBuilder();
                SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                Date date = new Date();
                Guild guild = event.getGuild();
                embedDelete.setColor(Color.RED);
                embedDelete.setTitle("Ticket System");
                embedDelete.addField("" + event.getChannel().getName(),"", false);
                embedDelete.addField("Closed by", event.getMember().getAsMention(), true);
                embedDelete.addField("Datum", formatter.format(date), true);
                guild.getTextChannelById("").sendMessageEmbeds(embedDelete.build()).queue();
                event.getInteraction().getChannel().delete().queue();
            }
        } else if (event.getButton().getId().equals("claimButton")) {
            if (event.getInteraction().getMember().hasPermission(Permission.KICK_MEMBERS)) {
                TextChannel channel = event.getGuildChannel().asTextChannel();
                Guild guild = event.getGuild();
                event.getInteraction().getMessage().delete().queue();
                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(Color.YELLOW)
                        .setTitle("Ticket System")
                        .setDescription(event.getInteraction().getUser().getAsMention() + "has claimed the ticked");
                channel.sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();
                EmbedBuilder embedmod = new EmbedBuilder()
                        .setColor(Color.YELLOW)
                        .setTitle("Ticket System")
                        .setDescription(event.getInteraction().getUser().getAsMention() + "has claimed "+ "#" + event.getChannel().getName());
                guild.getTextChannelById("").sendMessageEmbeds(embedmod.build()).queue();
                IPermissionHolder iPermissionHolder = event.getGuild().getRoleById("");
                channel.upsertPermissionOverride(iPermissionHolder).setDenied(Permission.VIEW_CHANNEL).queue();
                channel.upsertPermissionOverride(event.getMember()).setAllowed(Permission.VIEW_CHANNEL).queue();
            }
        }
    }

    private Button closeButton() {
        return Button.danger("closeButton", "🔒" + "Close");
    }

    private Button claimButton() {
        return Button.success("claimButton", "Claim");
    }
}
