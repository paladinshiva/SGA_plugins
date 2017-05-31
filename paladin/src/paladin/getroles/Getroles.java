package paladin.getroles;
import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import paladin.cleanup.Cleanup;

public class Getroles extends CommandModule {

    public Getroles() {
        super("Get Roles");
        setVersion("4.0");
        setModuleNameAndAuthor("Getting Roles", "paladinshiva/Chase/Xia");
        addCommand(new String[]{
                "getps4", "getxb1",
                "getlfgps4", "getlfgxb1",
                "changename"
        });
    }

    @Override
    public void processCommand(String command, String nickname, MessageReceivedEvent mre) {
        {
            switch (command.toLowerCase()) {
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
                case "changename":
                    ChangeName(nickname, mre);
                    break;
            }
            new Cleanup(mre);
        }
    }

    private void ChangeName(String nickname, MessageReceivedEvent mre) {
        if (mre.getMember().isOwner() == true) {
            mre.getChannel().sendMessage("Cannot help. " + mre.getMember().getEffectiveName() + ", you are the BIG BOSS (Server Owner), or your role is higher than mine").queue();
        } else {
            if (nickname == null) {
                sendHelpString(mre, "Please include your Guardian Name: !changename your_name_here");
            } else {
                mre.getChannel().sendMessage("SGA Welcomes you, " + nickname + "!").queue();
                /**WILL THROW EXCEPTION OF ROLE POSITION IF USER IS ADMINISTRATOR*/
                mre.getGuild().getController().setNickname(mre.getMember(), nickname).queue();
            }
        }
    }
    private void Getps4(MessageReceivedEvent mre) {
        if (mre.getMember().isOwner() == true) {
            mre.getChannel().sendMessage("Cannot help. " + mre.getMember().getEffectiveName() + ", you are the BIG BOSS (Server Owner), or your role is higher than mine").queue();
        } else {
            String tag = "[PS4] " + mre.getMember().getNickname();
            mre.getGuild().getController().setNickname(mre.getMember(), tag).queue();
            mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("ps4", true)).queue();
            mre.getChannel().sendMessage("PS4 added, " + mre.getMember().getEffectiveName()+"!").queue();
        }
    }

    private void Getxb1(MessageReceivedEvent mre) {
        if (mre.getMember().isOwner() == true) {
            mre.getChannel().sendMessage("Cannot help. " + mre.getMember().getEffectiveName() + ", you are the BIG BOSS(Server Owner) or your role is higher than mine").queue();
        } else {
            String tag = "[XB1] " + mre.getMember().getNickname();
            mre.getGuild().getController().setNickname(mre.getMember(), tag).queue();
            mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("xb1", true)).queue();
            mre.getChannel().sendMessage("XBOX1 added, " + mre.getMember().getEffectiveName()+"!").queue();
        }
    }

    private void GetLFGPS4(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("ps4lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on PS4 is yours to use, "+mre.getMember().getEffectiveName()+"!").queue();
    }

    private void GetLFGXB1(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("xb1lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on XBOX1 is yours to use, "+mre.getMember().getEffectiveName()+"!").queue();
    }
}