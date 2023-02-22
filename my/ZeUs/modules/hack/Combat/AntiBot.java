package my.ZeUs.modules.hack.Combat;

import java.util.ArrayList;
import java.util.UUID;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.WSoundEvents;
import my.ZeUs.modules.Module;
import my.ZeUs.settings.Setting;
import net.minecraft.entity.Entity;
import shwepss.event.EventPacket;
import shwepss.event.EventPostAttack;
import shwepss.event.EventPostUpdate;
import shwepss.event.EventPreUpdate;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import net.minecraft.util.text.TextFormatting;

public class AntiBot extends Module {
	public static ArrayList<Entity> entete = new ArrayList();
	private UUID detectedEntity;

	public AntiBot() {
		super("AntiBot", 0, Category.Combat);
	}

	

	@EventTarget
	public void onUpdate(EventPostUpdate event) {this.isBot();}

	public void isBot() {

		Iterator var2 = getMC().world.playerEntities.iterator();
			for (final Entity e : getMC().world.loadedEntityList) {
				if (e.ticksExisted < 5 && e instanceof EntityOtherPlayerMP) {
					if (((EntityOtherPlayerMP) e).hurtTime > 0 && getMC().player.getDistanceToEntity(e) <= 25
							&& getMC().getConnection().getPlayerInfo(e.getUniqueID()).getResponseTime() != 0) {
						getMC().world.removeEntity(e);
					}
				}
			}
	}


	@EventTarget
	public void onPre(EventPreUpdate event) {
		for (Entity entity : getMC().world.loadedEntityList) {
			if (getMC().player != null) {
				return;
			}
		}
		}
	

	@EventTarget
	public void onPacket(EventPacket event) {
			if (event.getPacket() instanceof SPacketSpawnPlayer && getMC().player.ticksExisted >= 9) {
				if (((SPacketSpawnPlayer) event.getPacket()).getYaw() != 0
						&& ((SPacketSpawnPlayer) event.getPacket()).getPitch() != 0) {
					detectedEntity = ((SPacketSpawnPlayer) event.getPacket()).getUniqueId();
				}
			}
		}

	public void onEnable() {
		super.onEnable();
		EventManager.register(this);
		WSoundEvents.playButtonClick();
	}

	public void onDisable() {
		super.onDisable();
		EventManager.unregister(this);
		WSoundEvents.grass();
	}
}

