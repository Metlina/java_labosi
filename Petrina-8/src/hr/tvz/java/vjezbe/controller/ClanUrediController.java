package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.Datoteke;
import hr.tvz.java.vjezbe.controller.base.UrediBase;
import hr.tvz.java.vjezbe.entitet.Clan;

import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@SuppressWarnings("deprecation")
public class ClanUrediController extends UrediBase{

	@FXML
	private TextField oibClan;
	
	@FXML
	private TextField prezimeClan;
	
	@FXML
	private TextField imeClan;
	
	private boolean isEdit;
	
	private Clan zaPrikaz;
	
	private List<Clan> clanovi;
	
	public void urediParametri(List<Clan> clanovi, Clan zaPrikaz) {
		this.zaPrikaz = zaPrikaz;
		this.clanovi = clanovi;
		isEdit = true;
		oibClan.setText(zaPrikaz.getOIB());
		prezimeClan.setText(zaPrikaz.getPrezime());
		imeClan.setText(zaPrikaz.getIme());
	}

	@FXML
	public void initialize() {
		
	}
	

	@FXML
	private void unesiClana()
	{
		List<Clan> clanovi = null;
		
		if (!(validirajVrijednost(oibClan) & validirajVrijednost(prezimeClan) & validirajVrijednost(imeClan)))
		{
			Dialogs.create().title("Greška").message("Podaci nisu u ispravnom formatu!").showError();
			return;
		}
		
		if (isEdit)
		{
			clanovi = this.clanovi;
			clanovi.remove(zaPrikaz);
		}
		else
			clanovi = Datoteke.ucitajClana();
		
		clanovi.add(new Clan(imeClan.getText(), prezimeClan.getText(), oibClan.getText()));
		
		Datoteke.unesiClanove(clanovi);
		
		Dialogs.create().title("Informacija").message("Clan je uspješno unesen").showInformation();
	}
}