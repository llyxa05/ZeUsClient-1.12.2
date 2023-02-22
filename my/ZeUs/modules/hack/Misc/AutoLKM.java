package my.ZeUs.modules.hack.Misc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class AutoLKM extends Module{
	
    public AutoLKM() {
    	super("AutoLKM", Keyboard.KEY_NONE, Category.Misc);
    }
    


    
    public void onEnable() {
    	EventManager.register(this);
    	WSoundEvents.playButtonClick();
    }
    
    @EventTarget
    public void onTick(EventPreUpdate e) {
    	
    	if(getMC().currentScreen == null) {
            Robot bot;
			try {
				bot = new Robot();
				bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
               
       }
    }
    
    public void onDisable() {
    	EventManager.unregister(this);
        WSoundEvents.grass();
    }
}

