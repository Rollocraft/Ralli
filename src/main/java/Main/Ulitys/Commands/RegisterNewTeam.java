package Main.Ulitys.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class RegisterNewTeam extends ListenerAdapter {

    public void onSlashCommandInteraction (@NotNull SlashCommandInteractionEvent event) {
        UserSnowflake User = UserSnowflake.fromId(event.getUser().getId());
        String command = event.getName();
        if (command.equals("register")) {
            if (event.getMember().getRoles().isEmpty()){

                String UserName = event.getUser().getName();
                String Name = event.getOption("name").getAsString();

                //EMBED
                EmbedBuilder lock = new EmbedBuilder()
                        .setColor(Color.BLUE)
                        .setTitle("Service-Info")
                        .addField("name", " " + Name, false);
                if (event.getOption("team-members") != null){

                    String teammembers = event.getOption("team-members").getAsString();
                    lock.addField("Count of Teammembers", teammembers,true);
                }
                else
                    lock.addField("Count of Teammembers", "No Data/Input",true);
                lock.addField("Role:", "Ur Team role successfully got created", false);
                //

                Role role = event.getGuild().createRole()
                        .setName(Name + "  (Created by " + UserName + "!)")
                        .setColor(Color.orange)
                        .setMentionable(false)
                        .complete();

                event.replyEmbeds(lock.build()).setEphemeral(true).queue();
                event.getGuild().addRoleToMember(User, role).queue();
            }
            else
                event.reply("You already have registered ur Team!").setEphemeral(true).queue();
        }
    }
}

