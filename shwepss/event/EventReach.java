// 
// Decompiled by Procyon v0.5.30
// 

package shwepss.event;

import shwepss.eventapi.events.Event;

public class EventReach implements Event
{
    private float reach;
    
    public EventReach(final float reach) {
        this.reach = reach;
    }
    
    public float getReach() {
        return this.reach;
    }
    
    public void setReach(final float reach) {
        this.reach = reach;
    }
}
