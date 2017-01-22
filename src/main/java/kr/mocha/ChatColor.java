package kr.mocha;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import kr.mocha.command.BuyColorCommand;
import kr.mocha.command.SetColorCommand;
import me.onebone.economyapi.EconomyAPI;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * Created by user on 17. 1. 21.
 */
public class ChatColor extends PluginBase implements Listener {
    public static ChatColor instance;
    public Config config;
    public EconomyAPI api;

    @Override
    public void onEnable() {
        api = EconomyAPI.getInstance();
        getDataFolder().mkdirs();
        instance = this;
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("color_price", 5000);
        config = new Config(getDataFolder()+File.separator+"config.json", Config.JSON, map);
        this.getServer().getCommandMap().register("setcolor", new SetColorCommand());
        this.getServer().getCommandMap().register("buycolor", new BuyColorCommand());
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