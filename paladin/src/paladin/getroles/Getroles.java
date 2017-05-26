package paladin.getroles;

import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.impl.MemberImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import paladin.cleanup.Cleanup;

import java.util.Collection;

public class Getroles extends CommandModule {

    public Getroles() {
        super("Get Roles");
        setVersion("1.00");
        setModuleNameAndAuthor("Getting Roles", "paladinshiva/Chase/Xia");
        addCommand(new String[]{
                "getps4", "getxb1",
                "getlfgps4", "getlfgxb1",
                "gettagps4", "gettagxb1"
        });
    }

    @Override
    public void processCommand(String command, String arg1, MessageReceivedEvent mre) {
//        if (command.equals("gettagps4")) {
//            String tagd = String.format("[PS4]", arg1);
//            mre.getGuild().getController().setNickname(mre.getMember(),arg1).queue();
//            mre.getChannel().sendMessage("Thank you!").complete();
//
//        } else {
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
//            case "gettagps4":
//                GetTagPS4("",mre);
//                break;
//            case "gettagxb1":
//                GetTagXB1("",mre);
//                break;
        }
        new Cleanup(mre);
    }

    /*    public void GetTagPS4 (String gamertag, MessageReceivedEvent mre) {
            String tagd = String.format("[PS4]", gamertag);
            mre.getGuild().getController().setNickname((mre.getMember()), tagd).queue();
            mre.getChannel().sendMessage("Thank you!").complete();
        }
        public void GetTagXB1 (String gamertag, MessageReceivedEvent mre) {
            String tagd = String.format("[XB1]", gamertag);
            mre.getGuild().getController().setNickname((mre.getMember()), tagd).queue();
            mre.getChannel().sendMessage("Thank you!").complete();
        }*/
//    public void Getps4 (MessageReceivedEvent mre) {
//        mre.getGuild().getController().addRolesToMember(mre.getMember(),mre.getGuild().getRoleById("286307705449349120")).queue();
//        mre.getChannel().sendMessage("You are ps4 now").complete();
//    }
    public void Getps4(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("ps4", true)).queue();
        mre.getChannel().sendMessage("You are ps4 now").complete();
    }

    //    public void Getps4 (MessageReceivedEvent mre) {
//        mre.getGuild().getController().addRolesToMember(mre.getMember(),mre.getGuild().getRoleById("286307705449349120")).queue();
//        mre.getChannel().sendMessage("You are ps4 now").complete();
//    }
    public void Getxb1(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("xb1", true)).queue();
        mre.getChannel().sendMessage("You are xb1 now").complete();
    }

    //    public void Getxb1 (MessageReceivedEvent mre) {
//        mre.getGuild().getController().addRolesToMember(mre.getMember(),mre.getGuild().getRoleById("255078886512001024")).queue();
//        mre.getChannel().sendMessage("You are xb1 now").complete();
//    }
    public void GetLFGPS4(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("ps4lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on PS4 you have").complete();
    }

    //    public void GetLFGPS4 (MessageReceivedEvent mre) {
//        mre.getGuild().getController().addRolesToMember(mre.getMember(),mre.getGuild().getRoleById("310654165577760768")).queue();
//        mre.getChannel().sendMessage("The LFG on PS4 you have").complete();
//    }
    public void GetLFGXB1(MessageReceivedEvent mre) {
        mre.getGuild().getController().addRolesToMember(mre.getMember(), mre.getGuild().getRolesByName("xb1lfg", true)).queue();
        mre.getChannel().sendMessage("The LFG on XB1 you have").complete();
    }
//    public void GetLFGXB1 (MessageReceivedEvent mre) {
//        mre.getGuild().getController().addRolesToMember(mre.getMember(),mre.getGuild().getRoleById("310654344133476352")).queue();
//        mre.getChannel().sendMessage("The LFG on XB1 you have").complete();
//    }
}