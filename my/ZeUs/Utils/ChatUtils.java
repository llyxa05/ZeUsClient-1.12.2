package my.ZeUs.Utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;




public class ChatUtils
{
	//colors
	public static String Dblue = ("\u00A71");
	public static String blue = ("\u00A79");
	public static String cyan = ("\u00A7b");
	public static String Dgreen = ("\u00A72");
	public static String green = ("\u00A7a");
	public static String red = ("\u00A7c");
	public static String Dred = ("\u00A74");
	public static String pink = ("\u00A7d");
	public static String gold = ("\u00A76");
	public static String purple = ("\u00A75");
	public static String gray = ("\u00A77");
	public static String white = ("\u00A7f");
	//formating
	public static String ss = ("\u00A7");
	public static String l = ("\u00A7l");
	public static String o = ("\u00A7o");
	public static String n = ("\u00A7n");
	public static String k = ("\u00A7k");
	public static String m = ("\u00A7m");
    //prefixes
	public static String YT = ("\u00A74\u00A7nYou\u00A7f\u00A7nTube\u00A7f\u00A7n");
	public static String hacker = ("\u00A7");
	public static String ehack = ("\u00A77\u00A7l[\u00A7b\u00A7lExtreme\u00A72\u00A7lHack\u00A77\u00A7l] \u00A7f\u00A7l");
	public static String ebot = ("\u00A7f\u00A7l[\u00A7b\u00A7lExtreme\u00A7a\u00A7lBot\u00A7f\u00A7l] ");
	public static String dev = ("\u00A7b\u00A7lCREATOR");
	public static String helper = ("\u00A79Helper");
	public static String friend = ("\u00A7aFriend");

	private static boolean enabled = true;
	
	public static void setEnabled(boolean enabled)
	{
		ChatUtils.enabled = enabled;
	}
	
	public static void component(ITextComponent component)
	{
		if(enabled)
			Minecraft.getMinecraft().ingameGUI.getChatGUI()
				.printChatMessage(new TextComponentString("")
					.appendSibling(component));
	}
	
	public static void message(String message)
	{
		component(new TextComponentString(message));
	}
	
	public static void warning(String message)
	{
		message("§c[§6§lWARNING§c]§f " + message);
	}
	
	public static void error(String message)
	{
		message("§c[§4§lERROR§c]§f " + message);
	}
	
	public static void success(String message)
	{
		message("§a[§2§lSUCCESS§a]§f " + message);
	}
	
	public static void failure(String message)
	{
		message("§c[§4§lFAILURE§c]§f " + message);
	}
	
	public static void cmd(String message)
	{
		Minecraft.getMinecraft().ingameGUI.getChatGUI()
			.printChatMessage(new TextComponentString(
				"§bExtremeHack§f §0§l<§aCMD§0§l>§f " + message));
	}
	public static void clear() {
		
	}
	
	public static String clearFormat(String s) {
		List<String> formats = new ArrayList<String>();
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '\u00a7') {
				formats.add(s.substring(i, Math.min(i+2, s.length())));
			}
		}
		
		for(String st : formats) {
			s = s.replace(st, "");
		}
		return s;
	}
}
