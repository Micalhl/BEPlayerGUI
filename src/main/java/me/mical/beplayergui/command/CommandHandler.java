package me.mical.beplayergui.command;

import me.mical.beplayergui.BEPlayerGUI;
import me.mical.beplayergui.command.subcommand.OpenCommand;
import org.serverct.parrot.parrotx.command.subcommands.HelpCommand;
import org.serverct.parrot.parrotx.command.subcommands.ReloadCommand;
import org.serverct.parrot.parrotx.command.subcommands.VersionCommand;

public class CommandHandler extends org.serverct.parrot.parrotx.command.CommandHandler {
    public CommandHandler() {
        super(BEPlayerGUI.getInstance(),
                "beplayergui");
        register(new OpenCommand());
        register(new ReloadCommand(plugin, "BEPlayerGUI.command.reload"));
        register(new HelpCommand(plugin));
        register(new VersionCommand(plugin));
    }
}
