package hr.tvz.java.vjezbe.controller;

import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import hr.tvz.java.vjezbe.baza.Datoteke;
import hr.tvz.java.vjezbe.controller.base.UrediBase;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

@SuppressWarnings("deprecation")
public class KnjigaUrediController extends UrediBase{
	
	@FXML
	private TextField nazivKnjiga;
	
	@FXML
	private ComboBox<String> vrstaKnjige;
	
	@FXML
	private TextField godinaKnjige;
	
	@FXML
	private TextField brStranicaKnjige;
	
	@FXML
	private ComboBox<String> jezikKnjige; 	
	
	@FXML
	private TextField nazivIzdavaca;
	
	@FXML
	private TextField drzavaIzdavaca;
	
	private boolean isEdit;
	
	private Knjiga zaPrikaz;
	
	private List<Knjiga> knjige;
	
	public void urediParametri(List<Knjiga> knjige, Knjiga zaPrikaz) {
		this.zaPrikaz = zaPrikaz;
		this.knjige = knjige;
		isEdit = true;
		nazivKnjiga.setText(zaPrikaz.getNaziv());
		vrstaKnjige.setValue(zaPrikaz.getVrstaPublikacije().toString());
		godinaKnjige.setText(zaPrikaz.getGodinaIzdanjaPublikacije() + "");
		brStranicaKnjige.setText(zaPrikaz.getBrojStranicaPublikacije() + "");
		jezikKnjige.setValue(zaPrikaz.getJezikKnjige().toString());
		nazivIzdavaca.setText(zaPrikaz.getIzdavac().getNazivIzdavaca());
		drzavaIzdavaca.setText(zaPrikaz.getIzdavac().getDrzavaIzdavaca());
	}

	@FXML
	public void initialize() {
		jezikKnjige.getItems().addAll(Jezik.vrijednosti());
		vrstaKnjige.getItems().addAll(VrstaPublikacije.vrijednosti());
	}
	
	@FXML
	private void unesiKnjigu() throws NeisplativoObjavljivanjeException 
	{
		List<Knjiga> knjige = null;
	
		if (!(validirajVrijednost(nazivIzdavaca) & validirajVrijednost(nazivKnjiga)	& validirajVrijednost(drzavaIzdavaca) & validirajVrijednost(vrstaKnjige) & validirajVrijednost(jezikKnjige)	& validirajBroj(godinaKnjige) & validirajBroj(brStranicaKnjige)))
		{
			Dialogs.create().title("Greška").message("Podaci nisu u ispravnom formatu!").showError();
			return;
		}
	
		if (isEdit) 
		{
			knjige = this.knjige;
			knjige.remove(zaPrikaz); 
		}
		else
			knjige = Datoteke.ucitajKnjige();
	
		Jezik jezik = Jezik.valueOf(jezikKnjige.getValue());
		
		double cijenaStranice = (jezik == Jezik.HRVATSKI) ? 0.45f : 0.75f;
		
		knjige.add(new Knjiga(
				nazivKnjiga.getText(), 
				jezik, 
				new Izdavac(nazivIzdavaca.getText(), drzavaIzdavaca.getText()), 
				Integer.parseInt(godinaKnjige.getText()), 
				VrstaPublikacije.valueOf(vrstaKnjige.getValue()), 
				Double.valueOf(cijenaStranice),
				Integer.parseInt(brStranicaKnjige.getText())));
		
		Datoteke.unesiKnjige(knjige);
	
		Dialogs.create().title("Informacija").message("Knjiga je uspješno unesena").showInformation(); 
	}
}