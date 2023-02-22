package my.ZeUs.settings;

import java.util.ArrayList;

import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;



public class SettingsManager {
	
	private static ArrayList<Setting> settings;
	
	public SettingsManager(){
		this.settings = new ArrayList<>();
	}
	
	public void rSetting(Setting in){
		this.settings.add(in);
	}
	
	public static ArrayList<Setting> getSettings(){
		return settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		if(out.isEmpty()){
			return null;
		}
		return out;
	}
	
	public Setting getSettingByName(String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)){
				return set;
			}
		}
		System.err.println("["+ Client.name + "] Error Setting NOT found: '" + name +"'!");
		return null;
	}

}