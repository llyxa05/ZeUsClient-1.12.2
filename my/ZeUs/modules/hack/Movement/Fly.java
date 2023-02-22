package my.ZeUs.modules.hack.Movement;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Helper;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;

public class Fly extends Module
{
    public Fly() {
    	super("Fly", Keyboard.KEY_NONE, Category.Movement);
    }
    
    @Override
    public void onEnable() {
    	ItemStack stack = Wrapper.getPlayer().getHeldItemMainhand();
    	stack.stackSize = 64;
    	Helper.sendPacket(new CPacketCreativeInventoryAction(36, stack));
        if (!Wrapper.getPlayer().capabilities.isCreativeMode) {
            Wrapper.getPlayer().jump();
        }
        WSoundEvents.playButtonClick();    
    }
    
    @Override
    public void onTick() {
        Wrapper.getPlayer().capabilities.isFlying = true;
    }
    
    @Override
    public void onDisable() {
        Wrapper.getPlayer().capabilities.isFlying = false;

        if (!Wrapper.getPlayer().onGround && Wrapper.getPlayer().capabilities.isCreativeMode) {
            Wrapper.getPlayer().jump();
            Wrapper.getPlayer().jump();
        }
        WSoundEvents.grass();
    }
}
