package shwepss.event;


import shwepss.eventapi.events.Event;
import net.minecraft.network.Packet;

public class EventPacket implements Event
{
  private EventPacketType type;
  private boolean cancelled = false;
  private Packet packet;
  
  public EventPacket(EventPacketType type, Packet packet)
  {
    this.type = type;
    this.packet = packet;
  }
  

  
  public Packet getPacket() {
    return this.packet;
  }
  
  public void setPacket(Packet packet) {
    this.packet = packet;
  }
  
  public EventPacketType getType() {
    return this.type;
  }
  
  public static enum EventPacketType
  {
    SEND, 
    RECEIVE;
  }

  public void setCancelled(boolean cancelled) {
	     this.cancelled = cancelled;
	   }
}


