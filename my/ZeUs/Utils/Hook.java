package my.ZeUs.Utils;

import java.util.*;

import my.ZeUs.modules.*;

public class Hook
{
    public static final void onTickIngame() {
        for (final Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onTick();
            }
        }
    }
    
    public static final void onRenderIngame() {
        for (final Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onRender();
            }
        }
    }
    
    public static final void onClickBlock(final int x, final int y, final int z) {
        for (final Module m : ModuleManager.getModules()) {
            if (m.isEnabled()) {
                m.onClickBlock(x, y, z);
            }
        }
    }
    
    public static final int onRightClickDelayTimer(final int i) {
        for (final Module m : ModuleManager.getModules()) {
            if (m.enabled && m.onRightClickDelayTimer(i) != i) {
                return m.onRightClickDelayTimer(i);
            }
        }
        return i;
    }
}
