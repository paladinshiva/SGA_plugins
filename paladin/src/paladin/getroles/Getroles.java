package paladin.getroles;


import java.awt.*;
import java.io.IOException;
import java.util.EventListener;
import java.util.List;
import com.reztek.Base.CommandModule;
import com.reztek.modules.GuardianControl.Guardian;
import com.reztek.modules.RumbleCommands.RumbleList;
import com.reztek.modules.TrialsCommands.Badges.TrialsDetailedBadge;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import javax.management.relation.RoleResult;

public class Getroles extends CommandModule {

    public Getroles() {
        super("Get Roles");
        setVersion("1.00");
        setModuleNameAndAuthor("Getting Roles", "paladinshiva/Chase/Xia");
        addCommand(new String[]{
                "getps4", "getxb1",
                "getLFGPS4", "getLFGXB1"
        });
    }

    @Override
    public void processCommand(String command, String arg1, MessageReceivedEvent mre) {
        switch (command) {
            case "getps4":
                Getps4(mre);
                break;
            case "getxb1":
                Getxb1(mre);
                break;
            case "getLFGPS4":
                GetLFGPS4(mre);
                break;
            case "getLFGXB1":
                GetLFGXB1(mre);
                break;

        }
        Cleanup(command, mre);
    }

    protected void Cleanup(String arg0, MessageReceivedEvent mre) {
        try {
        Thread.sleep(7000);                 //1000 milliseconds is one second.
    }
        catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
    List<Message> m = mre.getChannel().getHistory().retrievePast(2).complete();
        for (Message ms : m) {
        ms.delete().queue();
    }
}
    protected void Getps4 (MessageReceivedEvent mre) {
        mre.getMember();
        mre.getChannel().sendMessage("You are ps4 now").complete();
    }
    protected void Getxb1 (MessageReceivedEvent mre) {
        mre.getChannel().sendMessage("You are xb1 now").complete();
    }
    protected void GetLFGPS4 (MessageReceivedEvent mre) {
        mre.getChannel().sendMessage("The LFG on PS4 you have").complete();
    }
    protected void GetLFGXB1 (MessageReceivedEvent mre) {
        mre.getChannel().sendMessage("The LFG on PS4 you have").complete();
    }
}