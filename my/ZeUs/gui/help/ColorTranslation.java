package my.ZeUs.gui.help;

import org.lwjgl.opengl.*;

public class ColorTranslation
{
    public static void red() {
        GL11.glColor4f(255.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public static void green() {
        GL11.glColor4f(0.0f, 255.0f, 0.0f, 1.0f);
    }
    
    public static void blue() {
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 255.0f);
    }
    
    public static void aqua() {
        GL11.glColor4f(0.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void yellow() {
        GL11.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);
    }
    
    public static void black() {
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public static void friendColor() {
        GL11.glColor4f(231.0f, 82.0f, 99.0f, 199.0f);
    }
}
