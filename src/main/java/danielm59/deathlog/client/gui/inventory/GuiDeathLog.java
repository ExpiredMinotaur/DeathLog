package danielm59.deathlog.client.gui.inventory;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import danielm59.deathlog.handler.LogHandler;
import danielm59.deathlog.reference.LogGuiStates;
import danielm59.deathlog.reference.Reference;

public class GuiDeathLog extends GuiScreen
{
	public static GuiDeathLog log = new GuiDeathLog();

	public static final ResourceLocation texture = new ResourceLocation(
			Reference.LogGUI);

	private LogGuiStates guiState;

	private int xSize = 146;
	private int ySize = 180;
	private int left, top;

	private int buttonXSize = 113;
	private int buttonYSize = 15;
	private int buttonXLoc = 0;
	private int buttonYLoc = 180;

	@Override
	public final void initGui()
	{
		super.initGui();
		left = width / 2 - xSize / 2;
		top = height / 2 - ySize / 2;
		guiState = LogGuiStates.MENU;
		buttonList.add(new GuiButton(0, left + 18, top + 24, 112, 20,
				"Death Count"));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(left, top, 0, 0, xSize, ySize);

		switch (guiState)
		{

		case MENU:
			break;

		case COUNT:
			int i = 1;
			for (String player : LogHandler.getPlayers("COUNT"))
			{
				fontRendererObj.drawString(
						player + " : " + LogHandler.getDeaths(player),
						left + 18, top + 14 + (12 * i), 0x000000);
				i++;
			}
			break;

		default:
			break;
		}

		String title = "Death Log";
		fontRendererObj.drawString(title,
				left + xSize / 2 - fontRendererObj.getStringWidth(title) / 2,
				top + 12, 0x000000);

		super.drawScreen(par1, par2, par3);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
		case 0:
			guiState = LogGuiStates.COUNT;
			buttonList.clear();
			buttonList.add(new GuiButton(1, left + 18, top + 145, 110, 20,
					"Back"));
			break;
		case 1:
			guiState = LogGuiStates.MENU;
			buttonList.clear();
			buttonList.add(new GuiButton(0, left + 18, top + 24, 110, 20,
					"Death Count"));
			break;
		}
	}

}