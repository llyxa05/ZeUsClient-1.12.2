package my.ZeUs.modules.hack.Player;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import shwepss.event.EventPreUpdate;
import shwepss.event.EventSendPacket;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class AutoTool extends Module {
	
	public AutoTool() {
    	super("AutoTool", Keyboard.KEY_NONE, Category.Player);
    }
	
	public void onEnable(){
		EventManager.register(this);
		WSoundEvents.playButtonClick();
	}
		public void onDisable(){
		EventManager.unregister(this);
	    WSoundEvents.grass();
	}
	
		@EventTarget
		public void onPreUpdate(EventPreUpdate ev)
		    {
		        Minecraft mc = Minecraft.getMinecraft();
		        boolean overBlock;
		        boolean bl = overBlock = Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && mc.gameSettings.keyBindAttack.pressed;
		        if (overBlock) {
		            int slot;
		            mc.player.inventory.currentItem = slot = AutoTool.getBestTool(this.getBlock(Minecraft.getMinecraft().objectMouseOver.getBlockPos()));
		        }
		    }
		 @EventTarget
		    public void onPacket(EventSendPacket event) {
		        Minecraft mc = Minecraft.getMinecraft();
		        CPacketUseEntity goddamnpacket;
		        if (event.packet instanceof CPacketUseEntity && (goddamnpacket = (CPacketUseEntity)event.packet).getAction() == CPacketUseEntity.Action.ATTACK && AutoTool.getTheFuckingSwordYouDirtyCunt() != -1 && AutoTool.getTheFuckingSwordYouDirtyCunt() != mc.player.inventory.currentItem) {
		            mc.player.inventory.currentItem = AutoTool.getTheFuckingSwordYouDirtyCunt();
		        }
		    }
		 public static int getTheFuckingSwordYouDirtyCunt() {
		        Minecraft mc = Minecraft.getMinecraft();
		        float best = -1.0f;
		        int index = -1;
		        int i = 0;
		        while (i < 9) {
		            ItemStack itemStack = mc.player.inventory.getStackInSlot(i);
		            if (itemStack != null && itemStack.getItem() instanceof ItemSword) {
		                ItemSword sword = (ItemSword)itemStack.getItem();
		                float damage = sword.attackDamage + (float)EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStack) * 1.25f;
		                if (damage > best) {
		                    best = damage;
		                    index = i;
		                }
		            }
		            ++i;
		        }
		        return index;
		    }

		    public static int getBestTool(Block block) {
		        Minecraft mc = Minecraft.getMinecraft();
		        float best = -1.0f;
		        int slot = -1;
		        int i = 0;
		        while (i < 9) {
		            float str;
		            ItemStack itemStack = mc.player.inventory.getStackInSlot(i);
		            if (itemStack != null && (str = itemStack.getItem().getStrVsBlock(itemStack, block.getDefaultState())) > best) {
		                best = str;
		                slot = i;
		            }
		            ++i;
		        }
		        if (slot == -1) {
		            slot = 0;
		        }
		        return slot;
		    }
		    public static Block getBlock(BlockPos pos) {
		        return Minecraft.getMinecraft().world.getBlockState(pos).getBlock();
		    }
		    public static Block getBlock(int x, int y, int z) {
		        return Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock();
		    }
}
