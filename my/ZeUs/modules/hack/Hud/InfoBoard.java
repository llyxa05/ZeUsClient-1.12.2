package my.ZeUs.modules.hack.Hud;

import java.util.ArrayList;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;

public class InfoBoard extends Module
{
    public InfoBoard() {
    	super("InfoBoard", 0, Category.Hud);
    }
    
    public static boolean infoboard = false;
    
	public void onEnable() {
		this.infoboard = true;
	}
	
	
	public void onDisable() {
		this.infoboard = false;		
	}
}
