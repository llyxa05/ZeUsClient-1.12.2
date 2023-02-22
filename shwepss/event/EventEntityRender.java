package shwepss.event;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.*;
import shwepss.eventapi.events.Event;

public class EventEntityRender implements Event {

    public Render render;
    public EntityLivingBase entity;
    public double x, y, z;
	public Type type;

    public EventEntityRender(Type type, Render render, EntityLivingBase entity, double x, double y, double z) {
    	super();
        this.render = render;
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
