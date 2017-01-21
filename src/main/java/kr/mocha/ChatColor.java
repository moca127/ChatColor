package kr.mocha;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import kr.mocha.command.SetColorCommand;

import java.io.File;

/**
 * Created by user on 17. 1. 21.
 */
public class ChatColor extends PluginBase implements Listener {
    public static ChatColor instance;
    public Config config;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        instance = this;
        config = new Config(getDataFolder()+File.separator+"config.json", Config.JSON);
        this.getServer().getCommandMap().register("setcolor", new SetColorCommand());
        this.getServer().getPluginManager().registerEvents(this, this);
        super.onEnable();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(PlayerChatEvent event) {
        String msg = event.getMessage();
        Player player = event.getPlayer();
        String name = player.getName();

        if(!player.isOp())
            msg = msg.replaceAll("§.", "");
        if(config.exists(name))
            msg = "§"+config.get(name)+msg;
        event.setMessage(msg);
        return;
    }

    public static ChatColor getInstance() {
        return instance;
    }
}