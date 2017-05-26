package paladin.purge;
/**Included a little guide*/
import paladin.cleanup.Cleanup;
import java.util.Collection;
import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**Makes new class(in this case class is the name of your module inside Java),
 * attaching it to Chase's CommandModule which he made modular
 * Why is it good? because he provided easy(ier) method of adding Discord control command to the bot
 * He has setup layout that you can see below, as well as easy interface to define commands for the bot*/
public class Purge extends CommandModule {
        /**Sets up a constructor module(required by layout, as well as all the stuff below)*/
        public Purge() {
        /**Your module id*/
        super("Purge Command");
        setModuleNameAndAuthor("Purge Command", "paladinshiva/Chase/Xia");
        setVersion("2.0");
            /**Here you add your commands. If it is just one command then use: 'addCommand("name_here");'.
             * If you have several then use: 'addCommand(new String[] {"your_command","other_command"});' */
            addCommand(new String[]{
                "quickpurge", "slowpurge",
                "format:c"
        });
    }
    /**Makes sure your custom command new class(new relative to the plugin)will run,
     * after you already defined top class("public class your_module_here"*/
    @Override
    /**Layout provided by Chase. This is how it works: you have 2 arguments as strings and also MessageReceivedEvent
     * Now, String 'anything_here' defines your string name, for easy coding, because you can use it everywhere
     * mre just provides faster coding, instead of typing the whole thing
     * class could be pretty much anything except the higher one, but 'public void' works*/
    public void processCommand(String command, String count, MessageReceivedEvent mre) {
        /**Takes commands from top class*/
        if (command.equals("quickpurge"))  {
        /**Executes "quickpurge", deletes any amount from 2 to 100 messages at once
         * Asks what argument value is 'count' and if you forgot to say how much, returns help message*/
            if (count == null) {
                sendHelpString(mre, "Please specify how much from 2 to 100");
                /**If all is good then it goes to execute*/
            } else {
                /**Using Collection and Message functions of Java it goes into a channel, stores specified amount
                 * of messages(Discord messages, not that Java thing)
                 * Also equals the result of that history stored in the function to a simple variable*/
                Collection<Message> ms = mre.getChannel().getHistory().retrievePast(Integer.valueOf(count)).complete();
                /**Sends a message while its working(technically before), and completes its assigned CPU thread
                 * so the message would for sure stay there until code stops running*/
                mre.getChannel().sendMessage("Purging").complete();
                /**Makes the Collection<Message> function that we equaled to variable ms, a Message function
                 * that we in turn equal to m*/
                for (Message m : ms) {
                    /**Only will delete if messages are not pinned*/
                    if (!m.isPinned()) {
                        /**Delets all stored previously stuff*/
                        mre.getTextChannel().deleteMessages(ms).queue();
                    }
                }
                /**Runs my Cleanup sequence that wait 7 seconds and then deletes your command in channel
                 * and deletes the bot message*/
                new Cleanup(mre);
            }
        }
        /**Executes "slowpurge", deletes specified amount of messages one by one */
        if (command.equals("slowpurge") && mre.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if (count == null) {
                sendHelpString(mre, "Please specify how much from 1 to ~");
            } else {
                Collection<Message> ms = mre.getChannel().getHistory().retrievePast(Integer.valueOf(count)).complete();
                mre.getChannel().sendMessage("Purging").complete();
                for (Message m : ms) {
                    if (!m.isPinned()) {
                        m.delete().queue();
                    }
                }
                new Cleanup(mre);
            }
        }
        /**Executes "format:c", deletes and immediately adds the channel that command was executed in.
         * This cleans up unnecessary trashed channel with 1000s of messages */
        if (command.equals("format:c")&& mre.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            mre.getMember().getUser().openPrivateChannel().queue();
            mre.getMember().getUser().getPrivateChannel().sendMessage("EVERYTHING IS GONE").queue();
            mre.getTextChannel().delete().queue();
            String c = mre.getTextChannel().getName();
            mre.getGuild().getController().createTextChannel(c).queue();
        }
    }
}