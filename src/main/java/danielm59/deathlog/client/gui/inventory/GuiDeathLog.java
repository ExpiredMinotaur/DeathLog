package danielm59.deathlog.client.gui.inventory;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import danielm59.deathlog.reference.Reference;

public class GuiDeathLog extends GuiScreen
{
	public static GuiDeathLog log = new GuiDeathLog();

	public static final ResourceLocation texture = new ResourceLocation(
			Reference.LogGUI);

	private int xSize = 146;
	private int ySize = 180;
	private int left, top;

	@Override
	public final void initGui()
	{
		super.initGui();
		left = width / 2 - xSize / 2;
		top = height / 2 - ySize / 2;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
		super.drawScreen(par1, par2, par3);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

}