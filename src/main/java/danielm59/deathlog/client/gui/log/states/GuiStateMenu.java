package danielm59.deathlog.client.gui.log.states;

import net.minecraft.client.gui.GuiButton;
import danielm59.deathlog.client.gui.log.GuiDeathLog;
import danielm59.deathlog.reference.LogGuiStates;
import danielm59.deathlog.utility.LocalHelper;

public class GuiStateMenu extends BaseGuiState
{

	public GuiStateMenu(GuiDeathLog log)
	{
		super(log);
		name = "Menu";
	}

	@Override
	public void drawText()
	{
	}

	@Override
	protected void addButtons()
	{
		log.addButton(new GuiButton(0, log.getLeft() + 22, log.getTop() + 24, 120, 20,
				"Death Count"));
		log.addButton(new GuiButton(1, log.getLeft() + 22, log.getTop() + 46, 120, 20,
				"Death Types"));
	}

	@Override
	public void buttonClick(int buttonID)
	{
		switch (buttonID)
		{
		case 0:
			log.SetGuiState(LogGuiStates.COUNT);
			break;
		case 1:
			log.SetGuiState(LogGuiStates.TYPE_MENU);
			break;
		}
	}
	
	@Override
	public void init()
	{
		page = 1;
		addButtons();
	}
	
	@Override
	public void init(int page)
	{
		this.page = page;
		addButtons();
	}
}
