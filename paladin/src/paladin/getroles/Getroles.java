package paladin.getroles;

import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import paladin.cleanup.Cleanup;

public class Getroles extends CommandModule {

    public Getroles() {
        super("Get Roles");
        setVersion("1.00");
        setModuleNameAndAuthor("Getting Roles", "paladinshiva/Chase/Xia");
        addCommand(new String[]{
                "getps4", "getxb1",
                "getlfgps4", "getlfgxb1"
        });
    }

    @Override
    public void processCommand(String command, String arg0, MessageReceivedEvent mre) {

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
        }
        new Cleanup(mre);
    }
    private void Getps4 (MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember((mre.getMember()),mre.getGuild().getRolesByName("ps4", true)).queue();
        mre.getChannel().sendMessage("You are ps4 now").complete();
    }
    private void Getxb1 (MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember((mre.getMember()),mre.getGuild().getRolesByName("xb1", true)).queue();
        mre.getChannel().sendMessage("You are xb1 now").complete();
    }
    private void GetLFGPS4 (MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember((mre.getMember()),mre.getGuild().getRolesByName("ps4lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on PS4 you have").complete();
    }
    private void GetLFGXB1 (MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember((mre.getMember()),mre.getGuild().getRolesByName("xb1lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on XB1 you have").complete();
    }
}