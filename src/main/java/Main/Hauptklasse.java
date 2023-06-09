package Main;

import Main.TicketSystem.ButtonClickEvent;
import Main.TicketSystem.Command;
import Main.Ulitys.Commands.InfoService;
import Main.Ulitys.Commands.RegisterNewTeam;
import Main.Ulitys.Commands.help;
import Main.Ulitys.Listner.LeaveListner;
import Main.Ulitys.Listner.MemberListner;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.Objects;

public class Hauptklasse {
    public static JDA bot;

    public static void main(String[] args) throws InterruptedException{
        String token = "";

        bot = JDABuilder.createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Helping Rollocraft w/ coding"))
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy (MemberCachePolicy.ALL)
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.DIRECT_MESSAGE_TYPING,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_INVITES
                )
                .addEventListeners(
                        new InfoService(),
                        new RegisterNewTeam(),
                        new help(),
                        new Command(),
                        new ButtonClickEvent(),
                        new MemberListner(),
                        new LeaveListner()
                )
                .build().awaitReady();
        System.out.println("Online");



        Guild server = bot.awaitReady().getGuildById("1093089429322735666");

        Objects.requireNonNull(server).updateCommands().addCommands(
                Commands.slash("service", "service info")
                        .addOption(OptionType.STRING, "type", "The type of Service", true)
                        .addOption(OptionType.STRING, "info", "Info", true),
                Commands.slash("register", "Register your server/verify")
                        .addOption(OptionType.STRING, "name", "Enter the Name of ur Team", true)
                        .addOptions(new OptionData(OptionType.STRING, "color", "The Color of ur Team-role! (sadly we dont offer red)", true)
                                .addChoice("Yellow", "yellow")
                                .addChoice("Blue", "blue")
                                .addChoice("Green", "green")
                                .addChoice("Orange", "orange")
                                .addChoice("Magenta", "magenta")
                                .addChoice("Cyan", "cyan")
                                .addChoice("Pink", "pink")
                                .addChoice("Gray", "gray")
                        )
                        .addOption(OptionType.STRING, "team-members", "(optional) Enter the Amount of Member u have in ur Team", false)
                        ,
                Commands.slash("help", "gives a Help menu")

        ).queue();
    }
}
