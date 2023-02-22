package my.ZeUs.modules.hack.Render;


import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;

public class WallHack extends Module {
	
	public WallHack() {
		super("WallHack", Keyboard.KEY_NONE, Category.Render);
    }

	public static boolean WallHax = false;
	
	public void onEnable() {
		this.WallHax = true;
		WSoundEvents.playButtonClick();
	}
	
	public void onDisable() {
		this.WallHax = false;
		WSoundEvents.grass();
	}
	
}
