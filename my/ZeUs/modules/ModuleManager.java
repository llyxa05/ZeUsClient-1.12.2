package my.ZeUs.modules;

import java.util.ArrayList;
import java.util.Comparator;

import my.ZeUs.Utils.ClickGui;
import my.ZeUs.Utils.Client;
import my.ZeUs.config.ConfigSave;
import my.ZeUs.modules.hack.Combat.*;
import my.ZeUs.modules.hack.Hud.WaterMark;
import my.ZeUs.modules.hack.Misc.*;
import my.ZeUs.modules.hack.Movement.*;
import my.ZeUs.modules.hack.Player.*;
import my.ZeUs.modules.hack.Render.*;
import my.ZeUs.modules.hack.Hud.*;
import net.minecraft.client.Minecraft;

public class ModuleManager {
	
    public static final ArrayList<Module> modules = new ArrayList<Module>();
    
    public ModuleManager() {
        this.modules.add(new TriggerBot());
        this.modules.add(new Crits());
        this.modules.add(new BowAimbot());
        this.modules.add(new AutoArmor());
        this.modules.add(new KillAura());
        this.modules.add(new Velocity());
        this.modules.add(new FastBow());
        this.modules.add(new AntiBot());
        this.modules.add(new VerstakCrash());
        this.modules.add(new DiscorDRPC());
        this.modules.add(new AutoLKM());
        this.modules.add(new AutoPKM());
        this.modules.add(new AutoSprint());
        this.modules.add(new AirJump());
        this.modules.add(new Dolphin());
        this.modules.add(new Lift());
        this.modules.add(new Fly());
        this.modules.add(new SafeWalk());
        this.modules.add(new LongJump());
        this.modules.add(new ChestStealer());
        this.modules.add(new AutoRespawn());
        this.modules.add(new MiddlePearl());
        this.modules.add(new NoCollision());
        this.modules.add(new BedFucker());
        this.modules.add(new AutoTool());
        this.modules.add(new FastFall());
        this.modules.add(new InvWalk());
        this.modules.add(new Parkour());
        this.modules.add(new AntiAFK());
        this.modules.add(new Climb());
        this.modules.add(new MurderHack());
        this.modules.add(new FullBright());
        this.modules.add(new NoWeather());
        this.modules.add(new WaterMark());
        this.modules.add(new ClickGui());
        this.modules.add(new WallHack());
        this.modules.add(new Tracers());
        this.modules.add(new CubeESP());
        this.modules.add(new GlowESP());
        this.modules.add(new Day());
        this.modules.add(new NameTags());
        this.modules.add(new ChinaHat());
        this.modules.add(new BlocksESP());
        this.modules.add(new NoFall());
        this.modules.add(new ItemESP());
        this.modules.add(new Esp2D());
        this.modules.add(new TeslaAntiCrash());
        this.modules.add(new Configs());
        this.modules.add(new ConfigSave());
        this.modules.add(new MurderBA());
        this.modules.add(new ChatBypass());
        this.modules.add(new Trajectories());
        this.modules.add(new AutoBucket());
        this.modules.add(new BoatFly());
        this.modules.add(new AutoGApple());
        this.modules.add(new AutoTotem());
        this.modules.add(new Timer());
        this.modules.add(new DickESP());
        this.modules.add(new TargetHUD());
        this.modules.add(new InfoBoard());
        this.modules.add(new ArrayList1());
        this.modules.add(new Trails());
        this.modules.add(new BlockOverlay());
        this.modules.add(new XCarry());
        this.modules.add(new PngESP());
        
        this.getModuleByName("WaterMark").toggle();
        this.getModuleByName("AutoSprint").toggle();

        //Test
        this.modules.sort(Comparator.comparingInt(m1 -> Minecraft.getMinecraft().fontRendererObj.getStringWidth(((Module)m1).name)).reversed());
        
    }
    
    public static final Module getModuleByName(final String class1) {
        for (final Module module : getModules()) {
            if (module.getName().equalsIgnoreCase(class1)) {
                return module;
            }
        }
        return null;
    }
    
    public static Module getModule(Class<? extends Module> clazz) {
        for (Module mod : getModules()) {
            if (mod.getClass() == clazz) {
                return mod;
            }
        }
        return null;
    }
    
    public static ArrayList<Module> getModules() {
        return modules;
    }
}
