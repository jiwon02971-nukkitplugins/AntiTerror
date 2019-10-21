package iKguana.antiterror;

import cn.nukkit.plugin.PluginBase;

public class AntiTerror extends PluginBase {
	public void onEnable() {
		new LogAPI();
		getDataFolder().mkdirs();
		saveResource("config.yml", false);
		getServer().getPluginManager().registerEvents(new ATListener(this), this);
	}
}
