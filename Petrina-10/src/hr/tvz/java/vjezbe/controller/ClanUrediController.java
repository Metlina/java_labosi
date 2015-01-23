package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.controller.base.DialogHelper;
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
	
	//private List<Clan> clanovi;
	
	public void urediParametri(List<Clan> clanovi, Clan zaPrikaz) {
		this.zaPrikaz = zaPrikaz;
		//this.clanovi = clanovi;
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
		if (!(validirajVrijednost(oibClan) & validirajVrijednost(prezimeClan) & validirajVrijednost(imeClan)))
		{
			Dialogs.create().title("Greška").message("Podaci nisu u ispravnom formatu!").showError();
			return;
		}
		
		Clan clan = new Clan(imeClan.getText(), prezimeClan.getText(), oibClan.getText());
		
		try {
			if (isEdit) {
				clan.setId(zaPrikaz.getId());
				BazaPodataka.promijeniClan(clan);;;
		} else
			BazaPodataka.spremiClana(clan);;;
		} catch (Exception e) {
			DialogHelper.DatabaseError();
			e.printStackTrace();
			return;
		}
		
		Dialogs.create().title("Informacija").message("Knjiga je uspješno unesena").showInformation();
		
	}

}