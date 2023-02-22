package my.ZeUs.Utils;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.*;

public class SlickRenderer
{
    private static Minecraft mc;
    
    static {
        SlickRenderer.mc = Minecraft.getMinecraft();
    }
    
    public static void drawCenteredString(final UnicodeFont font, final float x, final float y, final String text, final int color) {
        renderString(font, x - font.getWidth(text) / 2, y, text, color);
    }
    
    public static void drawString(final UnicodeFont font, final float x, final float y, final String text, final int color) {
        renderString(font, x, y, text, color);
    }
    
    public static int drawStringWithShadow(final UnicodeFont font, final float x, final float y, final String text, final int color) {
        renderShade(font, x + 1.0f, y + 1.0f, text, color);
        return renderString(font, x, y, text, color);
    }
    
    public static int drawStringWithShadow2(final UnicodeFont font, final float x, final float y, final String text, final int color) {
        return renderString(font, x, y, text, color);
    }
    
    public static int renderString(final UnicodeFont font, float x, float y, final String text, final int color) {
        if (text == null) {
            return 0;
        }
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        disableDefaults();
        int width = 0;
        int i = 0;
        x *= 2.0f;
        y *= 2.0f;
        boolean sW = false;
        try {
            if (text.contains("§")) {
                final String[] messages = text.split("§");
                if (!text.startsWith("§")) {
                    font.drawString(x, y, messages[0], new Color(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color)));
                    width += font.getWidth(messages[0]);
                    sW = true;
                }
                while (i != messages.length) {
                    if (i != 0 || !sW) {
                        final String str = messages[i];
                        if (str.length() != 0) {
                            char identifier = 'z';
                            try {
                                identifier = str.charAt(0);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            final int index = "0123456789abcdefklmno".indexOf(new StringBuilder().append(identifier).toString().toLowerCase());
                            if (index != -1) {
                                final int colorcode = SlickRenderer.mc.fontRendererObj.colorCode[index];
                                messages[i] = removeCharAt(messages[i], 0);
                                font.drawString(x + width + 2.0f, y + 2.0f, messages[i], Color.black);
                                if (index == "0123456789abcdefk".indexOf(new StringBuilder().append(identifier).toString().toLowerCase())) {
                                    font.drawString(x + width, y, messages[i], new Color(getRedFromHex(colorcode), getGreenFromHex(colorcode), getBlueFromHex(colorcode)));
                                }
                                else {
                                    font.drawString(x + width, y, messages[i], new Color(getRedFromHex(color), getGreenFromHex(colorcode), getBlueFromHex(color)));
                                }
                                width += font.getWidth(messages[i]);
                            }
                        }
                    }
                    ++i;
                }
                enableDefaults();
                GL11.glScalef(2.0f, 2.0f, 2.0f);
                return width / 2;
            }
            font.drawString(x, y, text, new Color(getRedFromHex(color), getGreenFromHex(color), getBlueFromHex(color)));
            enableDefaults();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return font.getWidth(text) / 2 + 4;
    }
    
    public static void renderShade(final UnicodeFont font, float x, float y, final String text, final int color) {
        if (text == null) {
            return;
        }
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        disableDefaults();
        int width = 0;
        int i = 0;
        x *= 2.0f;
        y *= 2.0f;
        boolean sW = false;
        try {
            if (text.contains("§")) {
                final String[] messages = text.split("§");
                if (!text.startsWith("§")) {
                    font.drawString(x, y, messages[0], Color.black);
                    width += font.getWidth(messages[0]);
                    sW = true;
                }
                while (i != messages.length) {
                    if (i != 0 || !sW) {
                        final String str = messages[i];
                        if (str.length() != 0) {
                            char identifier = 'z';
                            try {
                                identifier = str.charAt(0);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            final int index = "0123456789abcdefk".indexOf(new StringBuilder().append(identifier).toString().toLowerCase());
                            if (index == -1) {
                                break;
                            }
                            messages[i] = removeCharAt(messages[i], 0);
                            font.drawString(x + width, y, messages[i], Color.black);
                            width += font.getWidth(messages[i]);
                        }
                    }
                    ++i;
                }
            }
            else {
                font.drawString(x, y, text, Color.black);
            }
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            enableDefaults();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static String removeCharAt(final String s, final int pos) {
        return String.valueOf(s.substring(0, pos)) + s.substring(pos + 1);
    }
    
    public static float getAlphaFromHex(final int color) {
        return (color >> 24 & 0xFF) / 255.0f;
    }
    
    public static float getRedFromHex(final int color) {
        return (color >> 16 & 0xFF) / 255.0f;
    }
    
    public static float getGreenFromHex(final int color) {
        return (color >> 8 & 0xFF) / 255.0f;
    }
    
    public static float getBlueFromHex(final int color) {
        return (color & 0xFF) / 255.0f;
    }
    
    public static void disableDefaults() {
        GL11.glEnable(3042);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(false);
    }
    
    public static void enableDefaults() {
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDepthMask(true);
    }
}
