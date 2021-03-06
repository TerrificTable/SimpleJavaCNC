package xyz.terrific.bot.command;

import xyz.terrific.bot.Bot;
import xyz.terrific.bot.command.commands.ExecuteShell;
import xyz.terrific.bot.command.commands.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author TerrificTable55
 * @version 1.0
 */
public class CommandManager {

    public static List<Command> commands    = new ArrayList<Command>();

    /**
     * @use CommandManager initialization, it adds commands to a `public commands` list
     */
    public CommandManager() {

        addCommand(new Test());
        addCommand(new ExecuteShell());

    }

    public void addCommand(Command command) {
        commands.add(command);
    }
    public static List<Command> getCommands() {
        return commands;
    }

    /**
     * @use Handle Commands, it takes a message and a bot as arguments, it will remove the `>`'s from the command and call the `command.onCommand()` function, returns a String
     * @param message
     * @param bot
     * @return
     */
    public String handleMessage(String message, Bot bot) {
        message = message.replace("> ", "");
        message = message.replace(">", "");
        boolean found = false;
        if (!(message.split(" ").length > 0)) return "";

        System.out.println(message);
        String commandName = message.split(" ")[0];

        for (Command cmd : commands) {
            if (cmd.aliases.contains(commandName) || cmd.name.equalsIgnoreCase(commandName)) {
                cmd.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message, bot);
                found = true;
                break;
            }
        }

        if (!found) {
            return "ERROR: Command not found";
        }

        return "Success";
    }
}
