package my.ZeUs.modules.hack.Combat;


import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.TimeUtil;
import my.ZeUs.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class AutoTotem extends Module
{
    private int xPos;
    private int yPos;
    private int zPos;
    private static int radius;
    TimeUtil time;
    
    static {
        AutoTotem.radius = 5;
    }
    
    public AutoTotem() {
        super("AutoTotem", 0, Category.Combat);
        this.time = new TimeUtil();
    }
    
    @Override
    public void onEnable() {
        EventManager.register(this);
    }
    
    @Override
    public void onDisable() {
        EventManager.unregister(this);
    }
    
    @EventTarget
    protected void onEvent(final EventPreUpdate e) {
        final Minecraft mc = Minecraft.getMinecraft();
        final ItemStack item = mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
        final NonNullList<ItemStack> iventorySlot = mc.player.inventory.mainInventory;
        for (int i = 0; i < iventorySlot.size(); ++i) {
            if (iventorySlot.get(i) != ItemStack.field_190927_a && (item == null || item.getItem() != Items.field_190929_cY) && iventorySlot.get(i).getItem() == Items.field_190929_cY) {
                mc.playerController.windowClick(0, (i < 9) ? (i + 36) : i, 0, ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
                mc.playerController.windowClick(0, (i < 9) ? (i + 36) : i, 0, ClickType.PICKUP, mc.player);
            }
        }
    }
}

