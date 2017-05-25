package paladin.purge;

import java.util.List;
import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Purge extends CommandModule {

    public Purge() {
        super("Purge Command");
        setModuleNameAndAuthor("Purge Command", "paladinshiva/Chase/Xia");
        setVersion("1.2");
        addCommand("purge");
    }

    @Override
    public void processCommand(String command, String count, MessageReceivedEvent mre) {
        if (command.equals("purge") && mre.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if (count == null) {
                sendHelpString(mre, "Please specify how many you would like to purge");
            } else {
                List<Message> ms = mre.getChannel().getHistory().retrievePast(Integer.valueOf(count)).complete();
                Message p = mre.getChannel().sendMessage("Purging").complete();
                    for (Message m : ms)
                        if (!m.isPinned()) {
                        m.delete().queue();
                        }
                p.delete().queue();
            }
        }
    }
}