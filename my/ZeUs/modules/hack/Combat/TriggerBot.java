package my.ZeUs.modules.hack.Combat;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class TriggerBot extends Module
{
	public TriggerBot() {
		super("TriggerBot", Keyboard.KEY_NONE, Category.Combat);
    }
    
	
	public void onEnable() {
		EventManager.register(this);
		WSoundEvents.playButtonClick();
	}
	
	
    @EventTarget
    public void onUpdate(final EventPreUpdate event) {
        if (this.getMC().objectMouseOver.entityHit != null && this.getMC().objectMouseOver.entityHit instanceof EntityLivingBase && this.getMC().player.getDistanceToEntity(this.getMC().objectMouseOver.entityHit) <= 3.8f && this.getMC().player.getCooledAttackStrength(0.0f) == 1.0f) {
            this.getMC().getConnection().sendPacket(new CPacketUseEntity(this.getMC().objectMouseOver.entityHit));
            this.getMC().player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public void onDisable() {
		 EventManager.unregister(this);
		 WSoundEvents.grass();
	}
}
