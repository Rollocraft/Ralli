package Main.Ulitys.Listner;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MemberListner extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        TextChannel channel = event.getGuild().getTextChannelById("");
        channel.sendMessage("Hey " + member.getUser().getName() + ", welcome to " + event.getGuild().getName()).queue();
    }
}
