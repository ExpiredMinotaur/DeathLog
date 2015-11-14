package danielm59.deathlog.client.gui.log;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import danielm59.deathlog.client.gui.log.states.BaseGuiState;
import danielm59.deathlog.client.gui.log.states.GuiStateCount;
import danielm59.deathlog.client.gui.log.states.GuiStateMenu;
import danielm59.deathlog.client.gui.log.states.GuiStateType;
import danielm59.deathlog.client.gui.log.states.GuiStateTypeMenu;
import danielm59.deathlog.handler.LogHandler;
import danielm59.deathlog.reference.LogGuiStates;
import danielm59.deathlog.reference.Reference;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiDeathLog extends GuiScreen
{
	public static GuiDeathLog log = new GuiDeathLog();

	public static final ResourceLocation texture = new ResourceLocation(
			Reference.LogGUI);

	private HashMap<LogGuiStates, BaseGuiState> states = new HashMap<LogGuiStates, BaseGuiState>();

	private BaseGuiState state;
	private String deathType = "";

	private Boolean stateLoaded = false;

	private String title = "Death Log";
	public ArrayList<String> deathTypes;

	float scale = 1.3F;
	private int xSize = (int) (scale * 512 / 2);
	private int ySize = (int) (scale * 316 / 2);
	private int left, top;

	private boolean initData = true;

	@Override
	public final void initGui()
	{
		super.initGui();
		left = width / 2 - xSize / 2;
		top = height / 2 - ySize / 2;
		deathTypes = LogHandler.getDeathTypes();
		if (initData)
		{
			initData = false;
			initStates();
			state = states.get(LogGuiStates.MENU);
		}
		state.init();
		stateLoaded = true;
	}

	public final void initStates()
	{
		states.put(LogGuiStates.MENU, new GuiStateMenu(log));
		states.put(LogGuiStates.COUNT, new GuiStateCount(log));
		states.put(LogGuiStates.TYPE_MENU, new GuiStateTypeMenu(log));
		states.put(LogGuiStates.TYPE, new GuiStateType(log));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.renderEngine.bindTexture(texture);
		func_146110_a(left, top, 0, 0, xSize, ySize, 256 * scale, 256 * scale);
		if (!stateLoaded)
		{
			state.init();
			stateLoaded = true;
		}
		state.drawText();
		fontRendererObj.drawString(title,
				left + xSize / 4 - fontRendererObj.getStringWidth(title) / 2,
				top + 12, 0x000000);
		fontRendererObj
				.drawString(state.getName(),
						left + 3 * xSize / 4
								- fontRendererObj
										.getStringWidth(state.getName()) / 2,
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
		state.buttonClick(button.id);
	}

	@Override
	public void onGuiClosed()
	{
		state = states.get(LogGuiStates.MENU);
	}

	public void addButton(GuiButton button)
	{
		buttonList.add(button);
	}

	public void disableButton(int id)
	{
		for (int k = 0; k < this.buttonList.size(); ++k)
		{
			GuiButton button = (GuiButton) this.buttonList.get(k);
			if (button.id == id)
			{
				button.enabled = false;
			}
		}
	}

	public void enableButton(int id)
	{
		for (int k = 0; k < this.buttonList.size(); ++k)
		{
			GuiButton button = (GuiButton) this.buttonList.get(k);
			if (button.id == id)
			{
				button.enabled = true;
			}
		}
	}

	public FontRenderer font()
	{
		return this.fontRendererObj;
	}

	public int getLeft()
	{
		return left;
	}

	public int getTop()
	{
		return top;
	}

	public void SetGuiState(LogGuiStates state)
	{
		this.state = states.get(state);
		buttonList.clear();
		stateLoaded = false;
	}

	public void loadPage(int page)
	{
		buttonList.clear();
		state.init(page);

	}

	public void setDeathType(String type)
	{
		deathType = type;
	}

	public String getDeathType()
	{
		return deathType;
	}
}