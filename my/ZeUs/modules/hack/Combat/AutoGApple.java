package my.ZeUs.modules.hack.Combat;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.client.*;
import net.minecraft.client.settings.*;
import net.minecraft.item.*;
import net.minecraft.entity.passive.*;

public class AutoGApple extends Module
{
    private boolean eating;
    
    public AutoGApple() {
        super("AutoGapple", 0, Category.Combat);
        this.eating = false;
        //this.health = Client.settingsManager.getSettingByName("").getValDouble();
    }
    
    @Override
    public void setup() {
    	Client.settingsManager.rSetting(new Setting("Health", this, 15, 1.0, 20.0, false));
    }
    
    @Override
    public void onTick() {
        if (Minecraft.getMinecraft().player.getHealth() + Minecraft.getMinecraft().player.getAbsorptionAmount() > Client.settingsManager.getSettingByName("Health").getValDouble() && this.eating) {
            this.eating = false;
            this.stop();
            return;
        }
        if (!this.canEat()) {
            return;
        }
        if (this.isFood(Minecraft.getMinecraft().player.getHeldItemOffhand()) && Minecraft.getMinecraft().player.getHealth() <= Client.settingsManager.getSettingByName("Health").getValDouble() && this.canEat()) {
            KeyBinding.setKeyBindState(this.getMC().gameSettings.keyBindUseItem.getKeyCode(), true);
            this.eating = true;
        }
        if (!this.canEat()) {
            this.stop();
        }
    }
    
    public static boolean isNullOrEmptyStack(final ItemStack itemStack) {
        return itemStack == null || itemStack.func_190926_b();
    }
    
    boolean isFood(final ItemStack itemStack) {
        return !isNullOrEmptyStack(itemStack) && itemStack.getItem() instanceof ItemAppleGold;
    }
    
    public boolean canEat() {
        return this.getMC().objectMouseOver == null || !(this.getMC().objectMouseOver.entityHit instanceof EntityVillager);
    }
    
    void stop() {
        KeyBinding.setKeyBindState(this.getMC().gameSettings.keyBindUseItem.getKeyCode(), false);
    }
}