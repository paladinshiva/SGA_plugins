package paladin.cleanup;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.List;

public class Cleanup {
    public Cleanup (MessageReceivedEvent mre) {
        try {
            Thread.sleep(7000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        List<Message> m = mre.getChannel().getHistory().retrievePast(2).complete();
        for (Message ms : m) {
            ms.delete().queue();
        }
    }
}
