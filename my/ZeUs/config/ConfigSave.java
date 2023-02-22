package my.ZeUs.config;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;

public class ConfigSave extends Module {

	public ConfigSave() {
		super("ConfigSave", 0, Category.Misc);
	}
	
	public void onEnable() {
	  if(Client.getInstance().config != null) {
         Client.getInstance().config.save();
      }
	  ChatUtils.success("Config saved!");
	  this.toggle();
	}

}
