package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.ChatColor;

/**
 * Created by user on 17. 1. 21.
 */
public class SetColorCommand extends Command {
    public Config config;

    public SetColorCommand() {
        super("setcolor", "Enable color chat", "/setcolor <player> <color>");
        setPermission("color.chat.cmd");
        config = ChatColor.getInstance().config;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            Player player = Server.getInstance().getPlayer(args[0]);
            config.set(player.getName(), args[1]);
            config.save();
            sender.sendMessage("§"+player.getName()+"'s chat color changed");
            player.sendMessage("§"+args[1]+"your chat color changed");
        }catch (Exception e) {
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }
}
