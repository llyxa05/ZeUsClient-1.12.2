package my.ZeUs.modules.hack.Misc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.modules.ModuleManager;
import my.ZeUs.settings.Setting;

public class Configs extends Module {
	

	public Configs(){
		super("ConfigLoad", 0, Category.Misc);
	}

	@Override
	public void onEnable() {
	Client.getInstance().config.load();
	ChatUtils.success("Last config loaded!");
	this.toggle();
	}
}
