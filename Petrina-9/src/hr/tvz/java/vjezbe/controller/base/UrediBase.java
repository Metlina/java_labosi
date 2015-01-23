package hr.tvz.java.vjezbe.controller.base;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public abstract class UrediBase {

	private void prikaziGresku(Control ctl, String message)
	{
		ctl.getStyleClass().add("error");
		ctl.setTooltip(new Tooltip(message));
	}
	private void ukloniGresku(Control ctl)
	{
		ctl.getStyleClass().remove("error");
		ctl.setTooltip(null);
	}
	
	protected boolean validirajVrijednost(Control ctl)
	{
		ukloniGresku(ctl);
		
		if (ctl instanceof TextField) 
		{
			if (((TextField) ctl).getText() != null && !((TextField) ctl).getText().equals(""))
				return true;
		}
		else if (ctl instanceof ComboBox<?>) 
		{
			if (((ComboBox<?>) ctl).getValue() != null)
				return true;
		}
		
		prikaziGresku(ctl, "Potrebno je unijeti vrijednost!");
		
		return false;
	}
	
	protected boolean validirajBroj(Control ctl)
	{
		ukloniGresku(ctl);
		
		if (ctl instanceof TextField)
		{
			if (((TextField) ctl).getText().matches("[0-9]+"))
				return true;
		}
		
		prikaziGresku(ctl, "Potrebno je unijeti broj!");
		
		return false;
	}
}
