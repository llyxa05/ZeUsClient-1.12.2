package my.ZeUs.modules.hack.Player;


import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;

public class MiddlePearl extends Module {
	
	public MiddlePearl() {
		super("MiddlePearl", Keyboard.KEY_NONE, Category.Player);
    }
	
	@Override
	public void onEnable() {
		EventManager.register(this);
		WSoundEvents.playButtonClick();
	}
	
	
    @EventTarget
    public void onPreMotion(EventPreUpdate event) {
        if (Mouse.isButtonDown(2)) {
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack = getMC().player.inventory.getStackInSlot(i);
                if (itemStack.getItem() != Items.ENDER_PEARL) continue;
                getMC().player.connection.sendPacket(new CPacketHeldItemChange(i));
                getMC().player.connection.sendPacket(new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                getMC().player.connection.sendPacket(new CPacketHeldItemChange(getMC().player.inventory.currentItem));
            }
        }
    }

    @Override
    public void onDisable() {
    	getMC().player.connection.sendPacket(new CPacketHeldItemChange(getMC().player.inventory.currentItem));
    	EventManager.unregister(this);
        WSoundEvents.grass();
    }
}
