package my.ZeUs.modules.hack.Movement;

import my.ZeUs.Utils.Category;
import my.ZeUs.modules.Module;
import net.minecraft.entity.item.EntityBoat;
import shwepss.event.EventPostUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class BoatFly extends Module {
	
    public BoatFly() {
        super("BoatFly", 0, Category.Movement);
    }

    public void onEnable() {
    	EventManager.register(this);
    }

    @EventTarget
    public void onPreMotion(EventPostUpdate event) {
        if (getMC().player.getRidingEntity() != null) {
            if (getMC().player.getRidingEntity() instanceof EntityBoat) {
              getMC().player.getRidingEntity().motionY = getMC().gameSettings.keyBindJump.pressed ? 0.5 : 0.0;
            }
        }
    }
    public void onDisable() {
	    EventManager.unregister(this);
    }
}
