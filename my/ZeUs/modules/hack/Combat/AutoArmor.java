package my.ZeUs.modules.hack.Combat;

import shwepss.event.*;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import shwepss.eventapi.*;

public class AutoArmor extends Module
{
	private int timer;
    private int lastUsed;
    private int Delaytimer;
    public static int delay;
    private int HEAD;
	
	public AutoArmor() {
		super("AutoArmor", Keyboard.KEY_O, Category.Combat);
    }
	

    @Override
    public void onEnable() {
        this.timer = 0;
        EventManager.register(this);
        WSoundEvents.playButtonClick();
    }
    
    @Override
    public void onDisable() {
        EventManager.unregister(this);
        WSoundEvents.grass();
    }
    
    @EventTarget
    public void onpreupdate(final EventPreUpdate e) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayerSP player = mc.player;
        if (this.timer > 0) {
            --this.timer;
            return;
        }
        if (mc.currentScreen instanceof GuiContainer && !(mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        final int[] bestArmorSlots = new int[4];
        final int[] bestArmorValues = new int[4];
        for (int armorType = 0; armorType < 4; ++armorType) {
            final ItemStack oldArmor = mc.player.inventory.armorItemInSlot(armorType);
            if (oldArmor != null && oldArmor.getItem() instanceof ItemArmor) {
                bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
            }
            bestArmorSlots[armorType] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            final ItemStack stack = mc.player.inventory.getStackInSlot(slot);
            if (stack != null) {
                if (stack.getItem() instanceof ItemArmor) {
                    final ItemArmor armor = (ItemArmor)stack.getItem();
                    final int armorType2 = this.getArmorType(armor);
                    final int armorValue = armor.damageReduceAmount;
                    if (armorValue > bestArmorValues[armorType2]) {
                        bestArmorSlots[armorType2] = slot;
                        bestArmorValues[armorType2] = armorValue;
                    }
                }
            }
        }
        for (int armorType = 0; armorType < 4; ++armorType) {
            int slot2 = bestArmorSlots[armorType];
            if (slot2 != -1) {
                final ItemStack oldArmor2 = mc.player.inventory.armorItemInSlot(armorType);
                if (oldArmor2 == null || !isEmptySlot(oldArmor2) || mc.player.inventory.getFirstEmptyStack() != -1) {
                    if (slot2 < 9) {
                        slot2 += 36;
                    }
                    mc.playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, mc.player);
                    mc.playerController.windowClick(0, slot2, 0, ClickType.QUICK_MOVE, mc.player);
                    break;
                }
            }
        }
        this.timer = 4;
    }
    
    public int getArmorType(final ItemArmor armor) {
        return armor.armorType.ordinal() - 2;
    }
    
    public static boolean isEmptySlot(final ItemStack slot) {
        return slot == null;
    }
}
       
    