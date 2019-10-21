package iKguana.antiterror;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityExplodeEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;

public class ATListener implements Listener {
	private AntiTerror plugin;

	public ATListener(AntiTerror plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent event) {
		if (plugin.getConfig().getBoolean("save-playerjoin-log"))
			LogAPI.saveLog("playerjoin", (event.getPlayer().isOp() ? "OP-" : "") + event.getPlayer().getName() + " " + event.getPlayer().getAddress());
	}

	@EventHandler
	public void playerQuitEvent(PlayerQuitEvent event) {
		if (plugin.getConfig().getBoolean("save-playerquit-log"))
			LogAPI.saveLog("playerquit", (event.getPlayer().isOp() ? "OP-" : "") + event.getPlayer().getName());
	}

	@EventHandler
	public void playerChatEvent(PlayerChatEvent event) {
		if (plugin.getConfig().getBoolean("save-chatlog"))
			LogAPI.saveLog("chat", event.getPlayer().getName() + " " + event.getMessage());
	}

	@EventHandler
	public void playerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		if (plugin.getConfig().getBoolean("save-cmdlog"))
			if (event.getPlayer().isOp())
				LogAPI.saveLog("command-op", event.getPlayer().getName() + " " + event.getMessage());
			else
				LogAPI.saveLog("command-user", event.getPlayer().getName() + " " + event.getMessage());
	}

	@EventHandler
	public void playerDropItemEvent(PlayerDropItemEvent event) {
		if (event.getPlayer().isOp()) {
			if (plugin.getConfig().getBoolean("save-drop-item-log-op"))
				LogAPI.saveLog("dropItem-op", event.getPlayer().getName() + " " + event.getItem().getName() + " " + event.getItem().getId() + ":" + event.getItem().getDamage() + " " + event.getItem().getCount());
		} else {
			if (plugin.getConfig().getBoolean("save-drop-item-log-user"))
				LogAPI.saveLog("dropItem-user", event.getPlayer().getName() + " " + event.getItem().getName() + " " + event.getItem().getId() + ":" + event.getItem().getDamage() + " " + event.getItem().getCount());
		}
	}

	@EventHandler
	public void explode(EntityExplodeEvent event) {
		if (plugin.getConfig().getBoolean("prevent-explode"))
			event.setCancelled();
	}
}
