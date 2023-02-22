package my.ZeUs.modules.hack.Player;

import java.io.IOException;
import java.net.UnknownHostException;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.TimeUtil;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class AntiAFK extends Module
{
    private TimeUtil timer;
    
    public AntiAFK() {
    	super("AntiAFK", Keyboard.KEY_NONE, Category.Player);
        this.timer = new TimeUtil();
    }
    
    public void onEnable() {
		EventManager.register(this);
		WSoundEvents.playButtonClick();
	}
    
    @EventTarget
    public void onUpdate(final EventPreUpdate event) throws UnknownHostException, IOException {
        if (this.timer.hasReached(1700.0)) {
            this.getMC().player.sendChatMessage("/aac");
            getMC().player.setRotationYawHead(10);
            getMC().player.jump();
            this.timer.reset();
        }
    }
    
    public void onDisable() {
		 EventManager.unregister(this);
		 WSoundEvents.grass();
	}
}
