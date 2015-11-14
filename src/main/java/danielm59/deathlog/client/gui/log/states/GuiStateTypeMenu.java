package danielm59.deathlog.client.gui.log.states;

import danielm59.deathlog.client.gui.log.GuiDeathLog;
import danielm59.deathlog.reference.LogGuiStates;
import danielm59.deathlog.utility.LocalHelper;
import net.minecraft.client.gui.GuiButton;

public class GuiStateTypeMenu extends BaseGuiState
{

	public GuiStateTypeMenu(GuiDeathLog log)
	{
		super(log);
		name = "Death Types";
	}

	@Override
	public void drawText()
	{
	}

	@Override
	protected void addButtons()
	{
		log.addButton(new GuiButton(0, log.getLeft() + 22, log.getTop() + 160,
				120, 20, "Back"));
		log.addButton(new GuiButton(1, log.getLeft() + 190, log.getTop() + 160,
				120, 20, "Next"));
		if (log.deathTypes.size() <= 12 * page)
		{
			log.disableButton(1);
		} else
		{
			log.enableButton(1);
		}
		for (int i = 0; i < Math.min(12,
				log.deathTypes.size() - (page - 1) * 12); i++)
		{
			if (i < 6)
			{
				String buttonName = LocalHelper.getLocalString(
						log.deathTypes.get(i + 12 * (page - 1)));
				log.addButton(new GuiButton(i + 2, log.getLeft() + 22,
						log.getTop() + 24 + (22 * i), 120, 20, buttonName));
			} else
			{
				String buttonName = LocalHelper.getLocalString(
						log.deathTypes.get(i + 12 * (page - 1)));
				log.addButton(new GuiButton(i + 2, log.getLeft() + 190,
						log.getTop() + 24 + (22 * (i - 6)), 120, 20,
						buttonName));
			}
		}
	}

	@Override
	public void buttonClick(int buttonID)
	{
		switch (buttonID)
		{
		case 0:
			if (page > 1)
			{
				log.loadPage(--page);
			} else
			{
				log.SetGuiState(LogGuiStates.MENU);
			}
			break;
		case 1:
			log.loadPage(++page);
			break;
		default:
			log.SetGuiState(LogGuiStates.TYPE);
			log.setDeathType((String) log.deathTypes
					.get(12 * (page - 1) + buttonID - 2));
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
