package my.ZeUs.Utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JOptionPane;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JFrame;

import org.lwjgl.opengl.Display;

import my.ZeUs.clickgui.ClickGUI;
import my.ZeUs.config.Config;
import my.ZeUs.modules.ModuleManager;
import my.ZeUs.settings.SettingsManager;
import net.minecraft.client.Minecraft;

public class Client {
	
	private static final Client INSTANCE = new Client();
	public static SettingsManager settingsManager;
	public static ModuleManager moduleManager;
	public static ClickGUI clickGUI; 
	public static Config config;
	private DiscordRP discordRP = new DiscordRP();

	public static final Client getInstance() {
		return INSTANCE;
	}
	
	public void init() {
		config = new Config();
		clickGUI = new ClickGUI();
		settingsManager = new SettingsManager();
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	public static String name = "ZeUs", version = "2.0";
	public static boolean torBypass = false;
	
	public static void startup() {
		settingsManager = new SettingsManager();
		moduleManager = new ModuleManager();
		clickGUI = new ClickGUI();
		
		System.out.println("Starting " + name + " - v" + version);
		
		try {
            HttpsURLConnection httpsClient = (HttpsURLConnection) new URL("https://llyxa05.github.io/ZeUsVersion/index.html").openConnection();
            httpsClient.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(httpsClient.getInputStream()));
            String readLine = buffer.readLine();
            if (readLine.equals(version)) {
            	System.out.println("Current ver: " + version + " readed into the site: " + readLine);
            } else {
            	 JOptionPane.showMessageDialog(null, "Привет! Вышла новая версия клиента! \n Обнову скачивать тут: https://dsc.gg/damazdav2 \n Советую обновиться!");
            } 
            
        } catch (Exception e) {
        	System.out.println("Error getting version into the site");
        }
		
		try {
			HttpsURLConnection httpsClient = (HttpsURLConnection) new URL("https://llyxa05.github.io/ZeUsVersion/googleanalystic.html").openConnection();
            httpsClient.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36");
		} catch (Exception e) {
        	System.out.println("Error getting google analystic");
        }
		Display.setTitle(name + " v" + version);
	}
	
}
