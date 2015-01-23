package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.Datoteke;
import hr.tvz.java.vjezbe.controller.base.UrediBase;
import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@SuppressWarnings("deprecation")
public class CasopisUrediController extends UrediBase{

	@FXML
	private TextField nazivCasopis;
	
	@FXML
	private TextField godinaCasopis;
	
	@FXML
	private TextField mjesecCasopisa;
	
	@FXML
	private ComboBox<String> vrstaCasopisa;
	
	//@FXML
	//private ComboBox<String> vrstaCasopisa = new ComboBox<>(
			//FXCollections.observableArrayList(VrstaPublikacije.values().toString()));
		
	@FXML
	private TextField brStranicaCasopis;

	private boolean isEdit;
	
	private Casopis zaPrikaz;
	
	private List<Casopis> casopisi;
	
	public void urediParametri(List<Casopis> casopisi, Casopis zaPrikaz) {
		this.zaPrikaz = zaPrikaz;
		this.casopisi = casopisi;
		isEdit = true;
		nazivCasopis.setText(zaPrikaz.getNaziv());
		godinaCasopis.setText(zaPrikaz.getGodinaIzdanjaPublikacije() + "");
		mjesecCasopisa.setText(zaPrikaz.getMjesecIzdavanjaCasopisa() + "");
		vrstaCasopisa.setValue(zaPrikaz.getVrstaPublikacije().toString());
		brStranicaCasopis.setText(zaPrikaz.getBrojStranicaPublikacije() + "");
	}

	@FXML
	public void initialize() {
		vrstaCasopisa.getItems().addAll(VrstaPublikacije.vrijednosti());
	}
	
	@FXML
	private void unesiCasopis() throws NeisplativoObjavljivanjeException
	{
		List<Casopis> casopisi = null;
		
		if (!(validirajVrijednost(nazivCasopis) & validirajBroj(godinaCasopis) & validirajBroj(godinaCasopis) & validirajVrijednost(vrstaCasopisa) & validirajBroj(brStranicaCasopis)))
		{
			Dialogs.create().title("Gre�ka").message("Podaci nisu u ispravnom formatu!").showError();
			return;
		}
		
		if (isEdit)
		{
			casopisi = this.casopisi;
			casopisi.remove(zaPrikaz);
		}
		else
			casopisi = Datoteke.ucitajCasopise();
		
		if (vrstaCasopisa.getValue() == "ELEKTRONICKA")
		{
		}
		else if (vrstaCasopisa.getValue() == "PAPIRNATA")
		{
		}
				
		casopisi.add(new Casopis(
				Integer.parseInt(mjesecCasopisa.getText()), 
				Integer.parseInt(godinaCasopis.getText()), 
				Integer.parseInt(brStranicaCasopis.getText()),
				VrstaPublikacije.valueOf(vrstaCasopisa.getValue()),
				nazivCasopis.getText()));
		
		Datoteke.unesiCasopise(casopisi);
		
		Dialogs.create().title("Informacija").message("Casopis je uspje�no unesen").showInformation(); 
	}
	
}