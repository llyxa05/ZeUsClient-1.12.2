package my.ZeUs.modules.hack.Combat;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;

public class Crits extends Module
{
    public Crits() {
    	super("Criticals", Keyboard.KEY_NONE, Category.Combat);
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
    
    @Override
    public void onTick() {
        if (Wrapper.getPlayer().onGround && Wrapper.getPlayer().isSwingInProgress && !Wrapper.getPlayer().isInWater() && !Wrapper.getPlayer().isOnLadder()) {
            final EntityPlayerSP player = Wrapper.getPlayer();
            final double posX = Wrapper.getPlayer().posX;
            final EntityPlayerSP player2 = Wrapper.getPlayer();
            player.setPosition(posX, player2.posY += 0.4, Wrapper.getPlayer().posZ);
        }
    }
}
