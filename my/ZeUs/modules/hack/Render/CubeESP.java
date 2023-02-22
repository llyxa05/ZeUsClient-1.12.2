package my.ZeUs.modules.hack.Render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import java.awt.Color;
import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ColorUtil;
import my.ZeUs.Utils.RenderUtils;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import shwepss.event.Event3DRender;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class CubeESP extends Module
{
	private final ArrayList<Entity> players = new ArrayList<>();
	private final ArrayList<EntityPlayer> players2 = new ArrayList<>();
	private final ArrayList<Blocks> chests = new ArrayList<>();
	int playerBox;

    public CubeESP() {
    	super("CubeESP", Keyboard.KEY_X, Category.Render);
    }
    
	@Override
	public void onEnable() {
		playerBox = GL11.glGenLists(1);
		GL11.glNewList(playerBox, GL11.GL_COMPILE);
		AxisAlignedBB bb = new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5);
		RenderUtils.drawOutlinedBox(bb);
		GL11.glEndList();
		EventManager.register(this);
		super.onEnable();
		WSoundEvents.playButtonClick();
	}

	@Override
	public void onDisable() {
		EventManager.unregister(this);
		super.onDisable();
		players.clear();
		disabler();
		WSoundEvents.grass();
	}

	@EventTarget
	public void on3D(Event3DRender e) {
		for (Entity en : getMC().world.loadedEntityList) {
			if (en instanceof EntityLivingBase) {
				 if(en instanceof EntityPlayer) {
				if (en.getName() == getMC().player.getName()) {

				} else {
					players.add(en);
				}
			}}
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glLineWidth(2);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		GL11.glPushMatrix();
		GL11.glTranslated(-getMC().getRenderManager().renderPosX, -getMC().getRenderManager().renderPosY,
				-getMC().getRenderManager().renderPosZ);

		// draw boxes
		renderBoxes(e.pticks());
		players.clear();

		GL11.glPopMatrix();

		// GL resets
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}
	
	public static void setColor(int color) {
		GL11.glLineWidth(3.3f);
        GL11.glColor4ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF), (byte) (color >> 24 & 0xFF));
    }

	
	private void renderBoxes(double partialTicks) {
		for (Entity e : players) {
			EntityLivingBase en = (EntityLivingBase)e;
			// set position
			GL11.glPushMatrix();
			GL11.glTranslated(e.prevPosX + (e.posX - e.prevPosX) * partialTicks,
					e.prevPosY + (e.posY - e.prevPosY) * partialTicks,
					e.prevPosZ + (e.posZ - e.prevPosZ) * partialTicks);
			GL11.glScaled(e.width + 0.2, e.height + 0.2, e.width + 0.2);
			if(e instanceof EntityPlayer) {
			

			 
			
			int customColorValue = ColorUtil.getRainbow();
			Color top = new Color(customColorValue);
			this.setColor(new Color(top.getRed(), top.getGreen(), top.getBlue(), 120).getRGB());

			// draw box
			GL11.glCallList(playerBox);

			GL11.glPopMatrix();
		}}
	}
	public void disabler() {
		for (Entity en : getMC().world.loadedEntityList) {
			if (en instanceof EntityLivingBase) {

				if (en.getName() == getMC().player.getName()) {

				} else {
					players.add(en);
					
				}
			}
		}
	}}
	
