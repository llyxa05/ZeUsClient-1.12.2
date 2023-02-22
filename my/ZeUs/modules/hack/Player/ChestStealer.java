package my.ZeUs.modules.hack.Player;


import net.minecraft.item.*;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.TimeUtil;
import my.ZeUs.Utils.Timer2;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.Utils.Wrapper;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class ChestStealer extends Module
{
    private Timer2 time;
    
    public ChestStealer() {
        super("ChestStealer", 0, Category.Player);
        this.time = new Timer2();
    }
    
    @Override
    public void setup() {
        Client.settingsManager.rSetting(new Setting("StealSpeed", this, 110.0F, 10.0F, 1000.0F, false));
        Client.settingsManager.rSetting(new Setting("AutoChestClose ", this, true));
    }
    
    public void onEnable() {
    	WSoundEvents.playButtonClick();
    }
    
    public void onDisable() {
        WSoundEvents.grass();
    }
    
    @Override
    public void onTick() {
        if (Wrapper.player().openContainer != null && Wrapper.player().openContainer instanceof ContainerChest) {
            final ContainerChest container = (ContainerChest)Wrapper.player().openContainer;
            for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); ++i) {
                if (container.getLowerChestInventory().getStackInSlot(i).getItem() != Item.getItemById(0) && this.time.check(Client.settingsManager.getSettingByName("StealSpeed").getValDouble())) {
                    Wrapper.mc().playerController.windowClick(container.windowId, i, 0, ClickType.QUICK_MOVE, Wrapper.player());
                    this.time.reset();
                }
                else if (this.empty(container)) {
                	if(Client.settingsManager.getSettingByName("AutoChestClose ").getValBoolean()) {
                		Wrapper.player().closeScreen();
                	}
                }
            }
        }
    }
    
    public boolean empty(final Container container) {
        boolean voll = true;
        for (int i = 0, slotAmount = (container.inventorySlots.size() == 90) ? 54 : 27; i < slotAmount; ++i) {
            if (container.getSlot(i).getHasStack()) {
                voll = false;
            }
        }
        return voll;
    }
}
