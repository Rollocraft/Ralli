package Main.Ulitys.Listner;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LeaveListner extends ListenerAdapter {
    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        Member member = event.getMember();
        TextChannel channel = event.getGuild().getTextChannelById("");
        channel.sendMessage( member.getUser().getName()+ " left :(" ).queue();

        }
}
