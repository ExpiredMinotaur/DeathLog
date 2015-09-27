package danielm59.deathlog.client.gui.log.states;

import danielm59.deathlog.client.gui.log.GuiDeathLog;

public abstract class BaseGuiState
{
	protected GuiDeathLog log;
	protected int page = 1;

	public BaseGuiState(GuiDeathLog log)
	{
		this.log = log;
	}
	
	public abstract void init();
	public abstract void drawText();
	protected abstract void addButtons();
	public abstract void buttonClick(int buttonID);	
}
