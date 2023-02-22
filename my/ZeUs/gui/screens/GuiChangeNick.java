package my.ZeUs.gui.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.minecraft.util.Session;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Keyboard;

import my.ZeUs.Utils.ChatUtils;
import my.ZeUs.Utils.ColorUtil;
import my.ZeUs.Utils.RandomUtils;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.gui.GuiScreen;

public class GuiChangeNick extends GuiScreen
{
    private GuiScreen parentScreen;
    private GuiTextField usernameTextField;
    public GuiChangeNick(final GuiScreen guiscreen) {
        this.parentScreen = guiscreen;
    
    }
    
    public void updateScreen() {
        this.usernameTextField.updateCursorCounter();
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
 
	protected void actionPerformed(final GuiButton guibutton) throws IOException {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id == 1) {
            this.mc.displayGuiScreen(this.parentScreen);
        }
        
        else if (guibutton.id == 0) {
        	if(!(this.usernameTextField.getText().isEmpty())) {
            this.mc.session = new Session(this.usernameTextField.getText(), "", "", "");
            this.mc.displayGuiScreen(this.parentScreen);
         
            
        }}
      
        if (guibutton.id == 2) {
            this.mc.session = new Session(RandomUtils.randomString(10), "", "", "");
            this.mc.displayGuiScreen(this.parentScreen);
        }
      
    }
    
    protected void keyTyped(final char c, final int i) throws IOException {
        this.usernameTextField.textboxKeyTyped(c, i);
      
        if (c == '\t' && this.usernameTextField.isFocused()) {
            this.usernameTextField.setFocused(false);
        }

        if (c == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
    }
    
    protected void mouseClicked(final int i, final int j, final int k) throws IOException {
        super.mouseClicked(i, j, k);
        this.usernameTextField.mouseClicked(i, j, k);
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
 
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 97 + 12, ChatUtils.cyan + "Done"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12, ChatUtils.blue + "Cancel"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 143 + 12, ChatUtils.green + "Random"));
        this.usernameTextField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 146, 200, 20);
    }
    
    public void drawScreen(final int i, final int j, final float f) {
    	  if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
         	 this.mc.displayGuiScreen(this.parentScreen);
         }
        this.drawDefaultBackground();
        this.drawGradientRect(0, this.height / 2 + 40, this.width, this.height, 111111111, ColorUtil.getRainbow());
        this.drawCenteredString(this.fontRendererObj, "Change nick", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRendererObj, "Nick", this.width / 2 - 100, 134, 10526880);
        this.usernameTextField.drawTextBox();
       // this.fakeNickField.drawTextBox();
        super.drawScreen(i, j, f);
    }
}