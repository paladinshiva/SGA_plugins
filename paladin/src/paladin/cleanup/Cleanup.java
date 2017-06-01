package paladin.cleanup;

import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.List;

public class Cleanup {
        public static final String PLUGIN_ID               = "GETROLES";
        public static final String PLUGIN_VER              = "4.1";
    public Cleanup (MessageReceivedEvent mre) {
        try {
            Thread.sleep(10000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        List<Message> ms = mre.getChannel().getHistory().retrievePast(2).complete();
        for (Message m : ms) {
            if (!m.isPinned()) {
                /**Delets all stored previously stuff*/
                m.delete().queue();
            }
        }
    }
}
