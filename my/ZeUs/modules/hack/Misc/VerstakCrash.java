package my.ZeUs.modules.hack.Misc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;

public class VerstakCrash extends Module
{
    public VerstakCrash() {
    	super("VerstakCrash", Keyboard.KEY_NONE, Category.Misc);
    }
    
    @Override
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onTick() {
		Minecraft mc = Minecraft.getMinecraft();
			if(mc.gameSettings.isKeyDown(mc.gameSettings.keyBindSneak)) {
				
				
	 try {
	    
		click1(442, 360);
		click1(498, 360);
	} catch (AWTException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}}
public static void click1(int x, int y) throws AWTException{
    Robot bot = new Robot();
    bot.mouseMove(x, y);    
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
}
public static void click2(int x, int y) throws AWTException{
    Robot bot = new Robot();
    bot.mouseMove(x, y);    
    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
}

    @Override
    public void onDisable() {
        WSoundEvents.grass();
    }
}
