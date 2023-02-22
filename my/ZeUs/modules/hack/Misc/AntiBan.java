package my.ZeUs.modules.hack.Misc;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.modules.Module;

public class AntiBan extends Module {
	
	public AntiBan() {
        super("AntiBan", Keyboard.KEY_NONE, Category.Misc);
    }
	
	public void onEnable(){
		if(getMC().getCurrentServerData().serverIP.contains("tesla")) {
			
		}
	}
}
