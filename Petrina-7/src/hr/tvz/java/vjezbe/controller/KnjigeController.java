package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
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

public class KnjigeController {
	
	public KnjigeController(){}
	
	@FXML
	private TextField nazivKnjige;
	
	@FXML
	private TableView<Knjiga> knjigaTable;
	
	@FXML
	private TableColumn<Knjiga, String> nazivKnjigeColumn;
	
	@FXML
	private TableColumn<Knjiga, VrstaPublikacije> vrstakKnjigeColumn;
	
	@FXML
	private TableColumn<Knjiga, Integer> godinaKnjigeColumn;
	
	@FXML
	private TableColumn<Knjiga, Integer> brojStranicaKnjigeColumn;
	
	@FXML
	private TableColumn<Knjiga, Jezik> jezikKnjigeColumn;
	
	@FXML
	private TableColumn<Knjiga, String> izdavacKnjigeColumn;
	
	@FXML
	public void initialize()
	{
		nazivKnjigeColumn.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("naziv"));
		vrstakKnjigeColumn.setCellValueFactory(new PropertyValueFactory<Knjiga, VrstaPublikacije>("vrstaPublikacije"));
		godinaKnjigeColumn.setCellValueFactory(new PropertyValueFactory<Knjiga, Integer>("godinaIzdanjaPublikacije"));
		brojStranicaKnjigeColumn.setCellValueFactory(new PropertyValueFactory<Knjiga, Integer>("brojStranicaPublikacije"));
		jezikKnjigeColumn.setCellValueFactory(new PropertyValueFactory<Knjiga, Jezik>("jezikKnjige"));
		izdavacKnjigeColumn.setCellValueFactory(new PropertyValueFactory<Knjiga, String>("izdavac"));
	}
	
	public void dohvatiKnjige()
	{
		List<Knjiga> listaKnjiga = ucitajKnjige();
		
		List<Knjiga> filtriranaListaKnjiga = new ArrayList<Knjiga>();
		
		if (nazivKnjige.getText().isEmpty() == false)
		{
			filtriranaListaKnjiga = listaKnjiga.stream().filter(p -> p.getNaziv().contains(nazivKnjige.getText())).collect(Collectors.toList());
		}
		else
		{
			filtriranaListaKnjiga = listaKnjiga;
		}
		
		ObservableList<Knjiga> observableListaKnjiga = FXCollections.observableArrayList(filtriranaListaKnjiga);
		knjigaTable.setItems(observableListaKnjiga);
	}
	
	public List<Knjiga> ucitajKnjige()
	{
		List<Knjiga> listaKnjiga = new ArrayList<Knjiga>();
		
		String fileName = "knjiga.txt";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Knjiga knjiga = null;
		
		while(true) 
		{
			try {
				knjiga = ucitajKnjigaIzDatoteka(reader);
			} catch (IOException e){
				e.printStackTrace();
			} catch (NeisplativoObjavljivanjeException e) {
				e.printStackTrace();
			}
			
			if(knjiga == null)
			{
				break;
			}
			
			listaKnjiga.add(knjiga);
		}
		
		return listaKnjiga;
	}
	
	private final Knjiga ucitajKnjigaIzDatoteka(BufferedReader reader) throws IOException, NeisplativoObjavljivanjeException
	{		
		String nazivKnjige = null, odabirString = null, odabirJezikaString = null, godinaKnjigeString = null, brojStranicaKnjigeString = null;
		Integer odabir = null, odabirJezika = null, godinaKnjige = null, brojStranicaKnjige = null;
		Double cijenaPoStranici = null;
		Jezik jezik = Jezik.HRVATSKI;
		VrstaPublikacije vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		//String naziv = null, drzavaIzdavaca = null;
		nazivKnjige = reader.readLine();
		
		if (nazivKnjige == null)
			return null;
		
		odabirString = reader.readLine();
		odabir = Integer.parseInt(odabirString);

		if (odabir == 1) {
			vrstaPublikacije = VrstaPublikacije.ELEKTRONICKA;
		} else if (odabir == 2) {
			vrstaPublikacije = VrstaPublikacije.PAPIRNATA;
		}
		
		Izdavac izdavac = unesiIzdavacaIzDatoteka(reader);
		
		//naziv = reader.readLine();
		//drzavaIzdavaca = reader.readLine();
		
		godinaKnjigeString = reader.readLine();
		godinaKnjige = Integer.parseInt(godinaKnjigeString);
		
		odabirJezikaString = reader.readLine();
		odabirJezika = Integer.parseInt(odabirJezikaString);

		switch (odabirJezika) {
		case (1):
			jezik = Jezik.HRVATSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_HR;
			break;
		case (2):
			jezik = Jezik.ENGLESKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (3):
			jezik = Jezik.NJEMACKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (4):
			jezik = Jezik.FRANCUSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (5):
			jezik = Jezik.TALIJANSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (6):
			jezik = Jezik.RUSKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		case (7):
			jezik = Jezik.KINESKI;
			cijenaPoStranici = Knjiga.CIJENA_PO_STRANICI_STRANO;
			break;
		}
		
		brojStranicaKnjigeString = reader.readLine();
		brojStranicaKnjige = Integer.parseInt(brojStranicaKnjigeString);
		
		//Izdavac izdavac = new Izdavac( naziv, drzavaIzdavaca);
		Knjiga knjiga = new Knjiga(nazivKnjige, jezik, izdavac, godinaKnjige, vrstaPublikacije, cijenaPoStranici, brojStranicaKnjige);
		
		return knjiga;
	}
	
	private final Izdavac unesiIzdavacaIzDatoteka(BufferedReader reader) throws IOException 
	{
		String nazivIzdavaca = null, drzavaIzdavaca = null;
		nazivIzdavaca = reader.readLine();
		drzavaIzdavaca = reader.readLine();
		Izdavac noviIzdavac = new Izdavac(nazivIzdavaca, drzavaIzdavaca);
		return noviIzdavac;
		}
}