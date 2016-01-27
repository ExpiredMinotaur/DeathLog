package io.github.danielm59.deathlog.client.gui.log.states;

import io.github.danielm59.deathlog.client.gui.log.GuiDeathLog;
import io.github.danielm59.deathlog.handler.LogHandler;
import io.github.danielm59.deathlog.reference.LogGuiStates;
import net.minecraft.client.gui.GuiButton;

public class GuiStateCount extends BaseGuiState
{

	public GuiStateCount(GuiDeathLog log)
	{
		super(log);
		name = "Death Count";
	}

	@Override
	public void drawText()
	{
		int i = 1;
		for (String player : LogHandler.getPlayers("COUNT"))
		{
			log.font().drawString(player + " : " + LogHandler.getDeaths(player, "COUNT"), log.getLeft() + 18,
					log.getTop() + 14 + (12 * i), 0x000000);
			i++;
		}
	}

	@Override
	public void addButtons()
	{
		log.addButton(new GuiButton(0, log.getLeft() + 22, log.getTop() + 160, 120, 20, "Back"));
	}

	@Override
	public void buttonClick(int buttonID)
	{
		switch (buttonID)
		{
		case 0:
			log.SetGuiState(LogGuiStates.MENU);
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
