package io.github.danielm59.deathlog.client.gui.log.states;

import io.github.danielm59.deathlog.client.gui.log.GuiDeathLog;

public abstract class BaseGuiState
{
	protected GuiDeathLog log;
	protected int page = 1;
	protected String name = "";

	public BaseGuiState(GuiDeathLog log)
	{
		this.log = log;
	}

	public String getName()
	{
		return name;
	}

	public abstract void init();

	public abstract void init(int page);

	public abstract void drawText();

	protected abstract void addButtons();

	public abstract void buttonClick(int buttonID);

}
