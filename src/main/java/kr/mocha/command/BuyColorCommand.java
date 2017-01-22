package kr.mocha.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.ChatColor;
import me.onebone.economyapi.EconomyAPI;

/**
 * Created by user on 17. 1. 22.
 */
public class BuyColorCommand extends Command {
    public Config config;

    public BuyColorCommand() {
        super("buycolor", "buy color", "/buycolor <color>");
        setPermission("color.buy.cmd");
        config = ChatColor.getInstance().config;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        EconomyAPI api = ChatColor.getInstance().api;
        try {
            if(args[0].length()==1) {
                Player player = (Player) sender;
                if(api.myMoney(player) < config.getInt("color_price"))
                    sender.sendMessage(TextFormat.RED+"lack money!");
                else {
                    api.reduceMoney(player, config.getInt("color_price"));
                    config.set(player.getName(), args[0]);
                    config.save();
                    player.sendMessage("§" + args[0] + "your chat color changed");
                }
            }else sender.sendMessage(TextFormat.RED+"this is not color code!");
        } catch (Exception e) {
            sender.sendMessage(TextFormat.RED+getUsage());
        }
        return false;
    }

}
