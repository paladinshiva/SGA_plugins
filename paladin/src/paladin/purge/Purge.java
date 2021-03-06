package paladin.purge;

/**Included a little guide*/
import com.reztek.Utils.ConfigReader;
import paladin.cleanup.Cleanup;
import java.util.List;
import com.reztek.Base.CommandModule;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**Makes new class(in this case class is the name of your module inside Java),
 * attaching it to Chase's CommandModule which he made modular
 * Why is it good? because he provided easy(ier) method of adding Discord control command to the bot
 * He has setup layout that you can see below, as well as easy interface to define commands for the bot*/
public class Purge extends CommandModule {
    public static final String PLUGIN_ID               = "PURGE";
    public static final String PLUGIN_VER              = "3.6";
    public static final String PURGE_PERMISSION        = ConfigReader.GetConfigReader().getOrCreateConfigString("PURGE_PERMISSION", "MESSAGE_MANAGE");
    public static final String FORMAT_PERMISSION       = ConfigReader.GetConfigReader().getOrCreateConfigString("FORMAT_PERMISSION", "ADMINISTRATOR");
        /**Sets up a constructor module(required by layout, as well as all the stuff below)*/
        public Purge() {
            /**Your module id*/
            super(PLUGIN_ID);
            setModuleNameAndAuthor("Purge Command", "paladinshiva/Chase/Xia");
            setVersion(PLUGIN_VER);
                /**Here you add your commands. If it is just one command then use: 'addCommand("name_here");'.
                 * If you have several then use: 'addCommand(new String[] {"your_command","other_command"});' */
                addCommand(new String[]{
                    "purge", "format:c"
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
//        /**Takes commands from top class*/
//        if (command.equals("quickpurge"))  {
//        /**Executes "quickpurge", deletes any amount from 2 to 100 messages at once
//         * Asks what argument value is 'count' and if you forgot to say how much, returns help message*/
//            if (count == null) {
//                sendHelpString(mre, "How much from 2 to 100?");
//                /**If all is good then it goes to execute*/
//            } else {
//                /**Using Collection and Message functions of Java it goes into a channel, stores specified amount
//                 * of messages(Discord messages, not that Java thing)
//                 * Also equals the result of that history stored in the function to a simple variable*/
//                List<Message> ms = mre.getChannel().getHistory().retrievePast(Integer.valueOf(count)).complete();
//                /**Sends a message while its working(technically before), and completes its assigned CPU thread
//                 * so the message would for sure stay there until code stops running*/
//                mre.getChannel().sendMessage("Purging now "+mre.getMember().getEffectiveName()+"...").complete();
//                /**Makes the Collection<Message> function that we equaled to variable ms, a Message function
//                 * that we in turn equal to m*/
//                for (Message m : ms) {
//                    /**Only will delete if messages are not pinned*/
//                    if (!m.isPinned()) {
//                        /**Delets all stored previously stuff*/
//                        mre.getTextChannel().deleteMessages(ms).queue();
//                    }
//                }
//                /**Runs my Cleanup sequence that wait 7 seconds and then deletes your command in channel
//                 * and deletes the bot message*/
//                new Cleanup(mre);
//            }
//        }
        /**Executes "purge", deletes specified amount of messages one by one */
        if (command.equals("purge") && mre.getMember().hasPermission(Permission.valueOf(PURGE_PERMISSION))) {
            if (count == null) {
                sendHelpString(mre, "How much from 1 to ~?");
            } else {
                List<Message> ms = mre.getChannel().getHistory().retrievePast(Integer.valueOf(count)).complete();
                mre.getChannel().sendMessage("Purging now, "+mre.getMember().getEffectiveName()+"...").queue();
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
        if (command.equals("format:c")&& mre.getMember().hasPermission(Permission.valueOf(FORMAT_PERMISSION))) {
            String c = mre.getTextChannel().getName();
            mre.getTextChannel().delete().queue();
            mre.getGuild().getController().createTextChannel(c).queue();
            mre.getMember().getUser().openPrivateChannel().queue();
            mre.getPrivateChannel().sendMessage("EVERYTHING IS GONE").queue();
        }
    }
}