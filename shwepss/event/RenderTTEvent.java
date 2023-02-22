package shwepss.event;

import net.minecraft.item.ItemStack;
import shwepss.eventapi.events.Event;
import shwepss.eventapi.events.callables.EventCancellable;

public class RenderTTEvent  extends EventCancellable implements Event {
    private ItemStack stack;
    private int x, y;

    public RenderTTEvent(ItemStack stack, int x, int y) {
        this.stack = stack;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ItemStack getStack() {
        return stack;
    }
}