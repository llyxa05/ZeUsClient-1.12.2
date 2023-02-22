package my.ZeUs.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import my.ZeUs.Utils.Client;
import my.ZeUs.modules.Module;
import my.ZeUs.modules.ModuleManager;
import my.ZeUs.settings.Setting;
import my.ZeUs.settings.SettingsManager;
import net.minecraft.client.Minecraft;

public class Config {
    public File dir;
    public File configs;
    public File dataFile;

    public Config() {
        this.dir = new File(Minecraft.getMinecraft().mcDataDir, "ZeUs");
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        this.dataFile = new File(this.dir, "config.txt");
        if (!this.dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            }
            catch (IOException var2) {
                var2.printStackTrace();
            }
        }
      
    }

    public void save() {
        ArrayList<String> toSave = new ArrayList<String>();
        for (Module mod : ModuleManager.getModules()) {
            toSave.add("Module:" + mod.getName() + ":" + mod.isEnabled() + ":" + mod.getKey());
        }
        SettingsManager var10000 = Client.settingsManager;
        for (Setting set : SettingsManager.getSettings()) {
            if (set.isCheck()) {
                toSave.add("Setting:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValBoolean());
            }
            if (set.isCombo()) {
                toSave.add("Setting:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
            }
            if (!set.isSlider()) continue;
            toSave.add("Setting:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
        }
        try {
            PrintWriter pw = new PrintWriter(this.dataFile);
            for (String str : toSave) {
                pw.println(str);
            }
            pw.close();
        }
        catch (FileNotFoundException var5) {
            var5.printStackTrace();
        }
    }

    public void load() {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
            String s = reader.readLine();
            while (s != null) {
                lines.add(s);
                s = reader.readLine();
            }
            reader.close();
     
        for (String s1 : lines) {
            Setting set;
            Module m;
            String[] args = s1.split(":");
            if (s1.toLowerCase().startsWith("module:")) {
                m = ModuleManager.getModuleByName(args[1]);
                if (m == null) continue;
                if (!m.getName().contains("Config")) {
                	if(!m.enabled) {
                	if(Boolean.parseBoolean(args[2])) {
                		m.setEnabled(Boolean.parseBoolean(args[2]));
                	}
                	}
                }
                m.setKey(Integer.parseInt(args[3]));
                continue;
            }
            if (!s1.toLowerCase().startsWith("setting:") || (m = ModuleManager.getModuleByName(args[2])) == null || (set = Client.settingsManager.getSettingByName(args[1])) == null) continue;
            if (set.isCheck()) {
                set.setValBoolean(Boolean.parseBoolean(args[3]));
            }
            if (set.isCombo()) {
                set.setValString(args[3]);
            }
            if (!set.isSlider()) continue;
            set.setValDouble(Double.parseDouble(args[3]));
            set.setValDouble(Float.parseFloat(args[3]));
        }
    
    }
    catch (Exception var7) {
        var7.printStackTrace();
    }
    }
}