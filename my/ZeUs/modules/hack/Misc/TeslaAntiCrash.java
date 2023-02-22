package my.ZeUs.modules.hack.Misc;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.modules.Module;
import net.minecraft.network.play.client.CPacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketResourcePackStatus.Action;
import net.minecraft.network.play.server.SPacketCamera;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketResourcePackSend;
import net.minecraft.util.text.ITextComponent;
import shwepss.event.EventPacketRecieve;
import shwepss.eventapi.EventManager;
import shwepss.eventapi.EventTarget;

public class TeslaAntiCrash extends Module {
	
	public TeslaAntiCrash() {
    	super("TeslaAntiCrash", Keyboard.KEY_NONE, Category.Misc);
    }
	
	   public void onEnable()
	    {
	    	EventManager.register(this);
	    }

	    public void onDisable()
	    {
	    	EventManager.unregister(this);
	    }
	    
	    private final Pattern pattern = Pattern.compile(".*\\$\\{[^}]*}.*");
	    
	    @EventTarget
	    public void checkExploit(EventPacketRecieve e) {
	        // Check Explosion Packet
	        if (e.getPacket() instanceof SPacketExplosion) {
	            SPacketExplosion packet = (SPacketExplosion) e.packet;
	            if (Math.abs(packet.motionX) > 99 || Math.abs(packet.motionY) > 99 || Math.abs(packet.motionZ) > 99) {
	                this.processExploit("Explosion", "A");
	                e.setCancelled(true);
	            } else if (Math.abs(packet.strength) > 99) {
	                this.processExploit("Explosion", "B");
	                e.setCancelled(true);
	            }
	        } else if (e.getPacket() instanceof SPacketChat) {
	            SPacketChat packet = (SPacketChat) e.packet;
	            Matcher matcher = this.pattern.matcher(packet.getChatComponent().getUnformattedText().toLowerCase());
	            if (matcher.find()) {
	                this.processExploit("Log4j", "A");
	                e.setCancelled(true);
	            }
	        } else if (e.getPacket() instanceof SPacketEntityTeleport) {
	            SPacketEntityTeleport packet = (SPacketEntityTeleport) e.packet;
	            if (Math.abs(packet.getX()) > 29999984 || Math.abs(packet.getZ()) > 29999984 || Math.abs(packet.getY()) > 29999984) {
	                this.processExploit("Teleport", "A");
	                e.setCancelled(true);
	            }
	        } else if (e.getPacket() instanceof SPacketParticles) {
	            SPacketParticles packet = (SPacketParticles) e.packet;
	            if (Math.abs(packet.getParticleSpeed()) > 10) {
	                this.processExploit("Particle", "A");
	                e.setCancelled(true);
	            } else if (packet.getParticleCount() > 500) {
	                this.processExploit("Particle", "B");
	                e.setCancelled(true);
	            }
	        } else if (e.getPacket() instanceof SPacketChangeGameState) {
	            SPacketChangeGameState packet = (SPacketChangeGameState) e.packet;
	            if (packet.getGameState() > 5 && (packet.getValue() > 99 || packet.getGameState() > 99)) {
	                this.processExploit("GameState", "A");
	                e.setCancelled(true);
	            }
	        } else if (e.getPacket() instanceof SPacketCamera) {
	            SPacketCamera packet = (SPacketCamera) e.packet;
	            if (!getMC().player.isSpectator()) {
	                this.processExploit("Camera", "A");
	                e.setCancelled(true);
	            }
	        } else if (e.getPacket() instanceof SPacketResourcePackSend) {
	            SPacketResourcePackSend packet = (SPacketResourcePackSend) e.packet;
	            try {
	                URI uri = new URI(packet.getURL());
	                String scheme = uri.getScheme();
	                boolean isLevelProtocol = "level".equalsIgnoreCase(scheme);
	                boolean isHttpProtocol = "http".equalsIgnoreCase(scheme);
	                boolean isHttpsProtocol = "https".equalsIgnoreCase(scheme);

	                if (!isHttpProtocol && !isHttpsProtocol && !isLevelProtocol) {
	                    this.processExploit("ResourcePack", "A");
	                    getMC().player.connection.sendPacket(new CPacketResourcePackStatus(Action.FAILED_DOWNLOAD));
	                    e.setCancelled(true);
	                }

	                if (isLevelProtocol && (packet.getURL().contains("..") || !packet.getURL().endsWith("/resources.zip"))) {
	                    this.processExploit("ResourcePack", "B");
	                    getMC().player.connection.sendPacket(new CPacketResourcePackStatus(Action.FAILED_DOWNLOAD));
	                    e.setCancelled(true);
	                }

	            } catch (URISyntaxException exception) {
	                this.processExploit("ResourcePack", "C");
	                getMC().player.connection.sendPacket(new CPacketResourcePackStatus(Action.FAILED_DOWNLOAD));
	                e.setCancelled(true);
	            }
	        }
	    }

	    private ITextComponent terminationReason = null;
	    
	    public void processExploit(String module, String type) {
            ChatUtils.message("The server tried to send exploit packet: " + module + " (type:" + type + ")");
      
       }
	    
	    public ITextComponent exitMessage()
	    {
	        return this.terminationReason;
	    }
	    
	    
}
