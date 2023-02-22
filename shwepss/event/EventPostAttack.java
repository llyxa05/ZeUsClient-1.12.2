package shwepss.event;

import net.minecraft.entity.Entity;
import shwepss.eventapi.events.Event;

public class EventPostAttack implements Event
{
    private Entity attacker;
    private Entity target;
    
    public EventPostAttack(final Entity attacker, final Entity target) {
        this.attacker = attacker;
        this.target = target;
    }
    
    public Entity getAttacker() {
        return this.attacker;
    }
    
    public Entity getTarget() {
        return this.target;
    }
}
