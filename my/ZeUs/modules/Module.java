package my.ZeUs.modules;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import my.ZeUs.Utils.Category;
import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.Client;
import my.ZeUs.Utils.Helper;
import my.ZeUs.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.*;
import shwepss.eventapi.EventManager;

public class Module
{
    public static ArrayList<String> Predictions;
    public boolean shown;
    public boolean enabled;
    public String name;
    public String command;
    public String desc;
    public Category category;
    public int color;
    public int key;
    protected boolean isEnabled;
    
    public Module(String name, int key, Category category) {
        this.name = name;
        this.key = key;
        this.category = category;
        setup();
     
    }
    
    public void runCommand(final String cmd) {
        this.onToggle();
        Helper.addMessage(String.valueOf(this.name) + (this.enabled ? " is now on." : " is now off."));
    }
    public static Minecraft getMC() {
        return Minecraft.getMinecraft();
    }
    public void onToggle() {
        this.toggle();
    }
    
    public void externalCommand(final String s) {
    }
    
    public void onEnable() {
    }
    
    public void onDisable() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.desc;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int k) {
        this.key = k;
    }
    
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isShown() {
        return this.shown;
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) {
            this.onEnable();
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
         
        }
        else {
            this.onDisable();
        }
    }
    
    public void setup() {}
    
    public void onPredicitons(final String s) {
        Module.Predictions.add(this.command);
    }
    
    public void onTick() {
    }
    
    public void onRender() {
    	
    }
    
    public void preMotionUpdate() {
    }
    
    public void postMotionUpdate() {
    }
    
    public void onClickBlock(final int x, final int y, final int z) {
    }
    
    public void onChat(final SPacketChat chat) {
    }
    
    public boolean onRightClick(final int par1, final int par2, final int par3, final int par4) {
        return false;
    }
    
    public int onRightClickDelayTimer(final int i) {
        return i;
    }
    
    public void onRender(double partilTicks) {
        
    }
    
    public Category getCategory() {
        return category;
    }
    
 
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public void toggleModule() {
	setState(!getState());
    }    

    public boolean setState(boolean state) {
	onToggle();
	if (state) {
	    onEnable();
	    this.isEnabled = true;
	  
	} else {
	    onDisable();
	    this.isEnabled = false;
        }
	
		return state;
	}
    
    public void setEnabled(boolean enabled) {
        this.enabled = !this.enabled;
        if (enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }

    public boolean getState() {
	return this.isEnabled;
    }
}