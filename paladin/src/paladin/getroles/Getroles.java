package paladin.getroles;

import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import paladin.cleanup.Cleanup;

public class Getroles extends CommandModule {

    public Getroles() {
        super("Get Roles");
        setVersion("3.0");
        setModuleNameAndAuthor("Getting Roles", "paladinshiva/Chase/Xia");
        addCommand(new String[]{
                "getps4", "getxb1",
                "getlfgps4", "getlfgxb1",
                "gettagps4", "gettagxb1"
        });
    }

    @Override
    public void processCommand(String command, String nickname, MessageReceivedEvent mre) {
        {
            switch (command) {
                case "getps4":
                    Getps4(mre);
                    break;
                case "getxb1":
                    Getxb1(mre);
                    break;
                case "getlfgps4":
                    GetLFGPS4(mre);
                    break;
                case "getlfgxb1":
                    GetLFGXB1(mre);
                    break;
                case "gettagps4":
                    GetTagPS4(nickname, mre);
                    break;
                case "gettagxb1":
                    GetTagXB1(nickname, mre);
                    break;
            }
            new Cleanup(mre);
        }
    }

    private void GetTagPS4(String nickname, MessageReceivedEvent mre) {
        if (mre.getMember().isOwner()) {
            mre.getChannel().sendMessage("Cannot help. "+mre.getMember().getEffectiveName()+", you are the BIG BOSS(Server Owner) or your role is higher than mine").queue();
        } else {
            if (nickname == null) {
                sendHelpString(mre, "Your Destiny Guardian name tell me you need: !gettagxxx your_name_here");
            } else {
                mre.getChannel().sendMessage("SGA Welcomes you, " + nickname + "!").queue();
                /**WILL THROW EXCEPTION OF ROLE POSITION IF USER IS ADMINISTRATOR*/
                mre.getGuild().getController().setNickname(mre.getMember(), "[PS4]" + nickname).queue();
            }
        }
    }
    private void GetTagXB1(String nickname, MessageReceivedEvent mre) {
        if (nickname == null) {
            sendHelpString(mre, "Your Destiny Guardian name tell me you need: !gettagxxx your_name_here");
        }  else {
            mre.getChannel().sendMessage("SGA Welcomes you, "+nickname+"!").queue();
            mre.getGuild().getController().setNickname(mre.getMember(), "[XB1]"+nickname).queue();
        }
    }

    private void Getps4(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("ps4", true)).queue();
        mre.getChannel().sendMessage("Ps4 tag now you have, "+mre.getMember().getEffectiveName()).queue();
    }

    private void Getxb1(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("xb1", true)).queue();
        mre.getChannel().sendMessage("Xb1 tag now you have, "+mre.getMember().getEffectiveName()).queue();
    }

    private void GetLFGPS4(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("ps4lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on PS4 now you have, "+mre.getMember().getEffectiveName()).queue();
    }

    private void GetLFGXB1(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("xb1lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on XB1 now you have, "+mre.getMember().getEffectiveName()).queue();
    }
}