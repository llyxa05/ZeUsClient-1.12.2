package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class SafeWalk extends Module {
	
	boolean shifted = false;
	
    public SafeWalk() {
    	super("SafeWalk", Keyboard.KEY_NONE, Category.Movement);
    }
    
    public void onEnable(){
    	EventManager.register(this);
    	WSoundEvents.playButtonClick();
    }
    
    @EventTarget
    public void onPreUpdate(EventPreUpdate e) {
             final BlockPos blockPos = new BlockPos(getMC().player.posX, getMC().player.posY - 1.0, getMC().player.posZ);
             if (getMC().player.fallDistance <= 4.0f) {
                 if (getMC().world.getBlockState(blockPos).getBlock() != Blocks.AIR) {
                     getMC().gameSettings.keyBindSneak.pressed = false;
                 }
                 else {
                     getMC().gameSettings.keyBindSneak.pressed = true;
                 }
        }
    }
    
    public void onDisable(){
    	EventManager.unregister(this);
        WSoundEvents.grass();
    }
}