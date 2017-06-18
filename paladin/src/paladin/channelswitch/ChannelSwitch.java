package paladin.channelswitch;

import com.reztek.Base.CommandModule;
import com.reztek.Base.CommandModule;
import com.reztek.Utils.BotUtils;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import paladin.cleanup.Cleanup;

import java.util.List;

public class ChannelSwitch extends CommandModule {
    public static final String PLUGIN_ID = "CHANNELSWITCH";
    public static final String PLUGIN_VER = "1";

    public ChannelSwitch() {
        super(PLUGIN_ID);
        setVersion(PLUGIN_VER);
        setModuleNameAndAuthor("Channel Switch", "paladinshiva");
        addCommand(new String[]{
                "showtrials", "hidetrials",
                "order"
        });
    }
    @Override
    public void processCommand(String command, String arg1, MessageReceivedEvent mre) {
        {
            switch (command.toLowerCase()) {
                case "showtrials":
                    ShowTrials(mre);
                    new Cleanup(mre);
                    break;
                case "hidetrials":
                    HideTrials(mre);
                    new Cleanup(mre);
                    break;
                case "order":
                    Order(mre);
                    break;
            }
        }
    }

    private void ShowTrials(MessageReceivedEvent mre) {
        List<TextChannel> ps = mre.getGuild().getTextChannelsByName("ps4_trials", true);
        List<TextChannel> xb = mre.getGuild().getTextChannelsByName("xb1_trials", true);
        String a = "[TC:ps4_trials(";
        String A = "[TC:xb1_trials(";
        String b = ")]";
        String B = ")]";
        String p = ps.toString().replace(a, "").replace(b, "");
        String x = xb.toString().replace(A, "").replace(B, "");
        mre.getGuild().getTextChannelById(p).getPermissionOverride(mre.getGuild().getPublicRole()).delete().queue();
        mre.getGuild().getTextChannelById(x).getPermissionOverride(mre.getGuild().getPublicRole()).delete().queue();
        mre.getChannel().sendMessage("ToO revealed!").queue();
//            mre.getChannel().sendMessage("ToO is already active!").queue();
    }
    private void HideTrials(MessageReceivedEvent mre) {
        List<TextChannel> ps = mre.getGuild().getTextChannelsByName("ps4_trials", true);
        List<TextChannel> xb = mre.getGuild().getTextChannelsByName("xb1_trials", true);
        String a = "[TC:ps4_trials(";
        String A = "[TC:xb1_trials(";
        String b = ")]";
        String B = ")]";
        String p = ps.toString().replace(a, "").replace(b, "");
        String x = xb.toString().replace(A, "").replace(B, "");
        mre.getGuild().getTextChannelById(p).createPermissionOverride(mre.getGuild().getPublicRole()).setDeny(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE, Permission.MESSAGE_HISTORY).queue();
        mre.getGuild().getTextChannelById(x).createPermissionOverride(mre.getGuild().getPublicRole()).setDeny(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE, Permission.MESSAGE_HISTORY).queue();
        mre.getChannel().sendMessage("ToO concealed!").queue();
    }

    private void Order(MessageReceivedEvent mre) {
        mre.getChannel().sendMessage(                   "```md\n" +
                "1.  #welcome"                                  + "\n" +
                "2.  #the_manual"                               + "\n" +
                "3.  #announcements"                            + "\n" +
                "4.  #sgaplaybook"                              + "\n" +
                "5.  #jedi_pvp_lfg"                             + "\n" +
                "6.  #courtyard"                                + "\n" +
                "7.  #clipdump"                                 + "\n" +
                "8.  #droid_commands"                           + "\n" +
                "9.  #jedi_library"                             + "\n" +
                "10. #ps4_trials"                               + "\n" +
                "11. #xb1_trials"                               + "\n" +
                "12. #pve"                                      + "\n" +
                "13. #suggestions"                              + "\n" +
                "14. #twitch_streams "                          + "\n" +
                "15. #jediconsortium (limited channel)"         + "\n" +
                "16. #highcouncil (limited channel)"            + "\n" +
                "17. #bot_development"                          + "\n" +
                "18. #social-media-projects (limited channel)"  + "\n" +
                "```"
        ).queue();
    }
}
