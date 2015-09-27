package danielm59.deathlog.client.gui.log.states;

import net.minecraft.client.gui.GuiButton;
import danielm59.deathlog.client.gui.log.GuiDeathLog;
import danielm59.deathlog.reference.LogGuiStates;
import danielm59.deathlog.utility.LocalHelper;

public class GuiStateTypeMenu extends BaseGuiState
{

	public GuiStateTypeMenu(GuiDeathLog log)
	{
		super(log);
	}

	@Override
	public void drawText()
	{
	}

	@Override
	protected void addButtons()
	{
		log.addButton(new GuiButton(0, log.getLeft() + 18, log.getTop() + 145,
				110, 20, "Back"));
		int i = 1;
		for (String type : log.deathTypes)
		{
			String buttonName = LocalHelper.getLocalString(type);
			log.addButton(new GuiButton(i, log.getLeft() + 18, log.getTop() + 24
					+ (22 * (i-1)), 110, 20, buttonName));
			i++;
		}
	}

	@Override
	public void buttonClick(int buttonID)
	{
		switch (buttonID)
		{
		case 0:
			log.SetGuiState(LogGuiStates.MENU);
			break;
		default:
			log.SetGuiState(LogGuiStates.TYPE);
			log.setDeathType((String) log.deathTypes.toArray()[buttonID-1]);
		}
	}

	@Override
	public void init()
	{
		page = 1;
		addButtons();
	}
}
