package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.iznimke.NeisplativoObjavljivanjeException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CasopisiController {

	public CasopisiController(){}
	
	@FXML
	private TextField nazivCasopisa;
	
	@FXML
	private TableView<Casopis> casopisTable;
	
	@FXML
	private TableColumn<Casopis, String> nazivCasopisaColumn;
	
	@FXML
	private TableColumn<Casopis, Integer> godinaCasopisaColumn;
	
	@FXML
	private TableColumn<Casopis, Integer> mjesecCasopisaColumn;
	
	@FXML
	private TableColumn<Casopis, VrstaPublikacije> vrstaCasopisaColumn;
	
	@FXML
	private TableColumn<Casopis, Integer> brojStranicaCasopisaColumn;
	
	@FXML
	public void initialize()
	{
		nazivCasopisaColumn.setCellValueFactory(new PropertyValueFactory<Casopis, String>("naziv"));
		godinaCasopisaColumn.setCellValueFactory(new PropertyValueFactory<Casopis, Integer>("godinaIzdanjaPublikacije"));
		mjesecCasopisaColumn.setCellValueFactory(new PropertyValueFactory<Casopis, Integer>("mjesecIzdavanjaCasopisa"));
		vrstaCasopisaColumn.setCellValueFactory(new PropertyValueFactory<Casopis, VrstaPublikacije>("vrstaPublikacije"));
		brojStranicaCasopisaColumn.setCellValueFactory(new PropertyValueFactory<Casopis, Integer>("brojStranicaPublikacije"));
	}

	public void dohvatiCasopise()
	{		
		List<Casopis> listaCasopisa = ucitajCasopise();
		
		List<Casopis> filtriranaListaCasopisa = new ArrayList<Casopis>();
		
		if (nazivCasopisa.getText().isEmpty() == false)
		{
			filtriranaListaCasopisa = listaCasopisa.stream().filter(p -> p.getNaziv().contains(nazivCasopisa.getText())).collect(Collectors.toList());
		}
		else
		{
			filtriranaListaCasopisa = listaCasopisa;
		}
		
		ObservableList<Casopis> observableListaCasopisa = FXCollections.observableArrayList(filtriranaListaCasopisa);
		casopisTable.setItems(observableListaCasopisa);
	}
	
	public List<Casopis> ucitajCasopise()
	{
		List<Casopis> listaCasopisa = new ArrayList<Casopis>();
		
		String fileName = "casopis.txt";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File (fileName)));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		Casopis casopis = null;
		
		while(true)
		{
			try {
				casopis = ucitajCasopiseIzDatoteke(reader);
			} catch (IOException e){
				e.printStackTrace();
			} catch (NeisplativoObjavljivanjeException e) {
				e.printStackTrace();
			}
			
			if(casopis == null)
			{
				break;
			}
			
			listaCasopisa.add(casopis);
		}
		
		return listaCasopisa;
	}
	
	private final Casopis ucitajCasopiseIzDatoteke(BufferedReader reader) throws IOException, NeisplativoObjavljivanjeException
	{
		String nazivCasopisa = null, godinaCasopisaString = null, brojStranicaCasopisaString = null, odabirString = null, mjesecCasopisaString = null;
		Integer godinaCasopisa = null, brojstranicaCasopisa = null, mjesecCasopisa = null, odabir = null;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;		
		
		nazivCasopisa = reader.readLine();
		
		if (nazivCasopisa == null)
			return null;
		
		godinaCasopisaString = reader.readLine();
		godinaCasopisa = Integer.parseInt(godinaCasopisaString);
		mjesecCasopisaString = reader.readLine();
		mjesecCasopisa = Integer.parseInt(mjesecCasopisaString);
		odabirString = reader.readLine();
		odabir = Integer.parseInt(odabirString);

		if (odabir == 1) {
			vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		} else if (odabir == 2) {
			vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
		}
		
		brojStranicaCasopisaString = reader.readLine();
		brojstranicaCasopisa = Integer.parseInt(brojStranicaCasopisaString);
		
		Casopis casopis = new Casopis(mjesecCasopisa, godinaCasopisa, brojstranicaCasopisa, vrstaPublikacije, nazivCasopisa);
		return casopis;
	}
}