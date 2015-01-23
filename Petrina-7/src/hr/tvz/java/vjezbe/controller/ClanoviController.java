package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.entitet.Clan;

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

public class ClanoviController {

	public ClanoviController() {}
	
	@FXML
	private TextField nazivClana;
	
	@FXML
	private TableView<Clan> clanoviTable;
	
	@FXML
	private TableColumn<Clan, String> imeColumn;
	
	@FXML
	private TableColumn<Clan, String> prezimeColumn;
	
	@FXML
	private TableColumn<Clan, String> oibColumn;
	
	@FXML
	public void initialize()
	{
		imeColumn.setCellValueFactory(new PropertyValueFactory<Clan, String>("ime"));
		prezimeColumn.setCellValueFactory(new PropertyValueFactory<Clan, String>("prezime"));
		oibColumn.setCellValueFactory(new PropertyValueFactory<Clan, String>("OIB"));
	}
	
	public void dohvatiClanove()
	{
		List<Clan> listaClanova = ucitajClana();
		
		List<Clan> filtriranaListaClanova = new ArrayList<Clan>();
		
		if (nazivClana.getText().isEmpty() == false)
		{
			filtriranaListaClanova = listaClanova.stream().filter(p -> p.getIme().contains(nazivClana.getText())).collect(Collectors.toList());
		}
		else
		{
			filtriranaListaClanova = listaClanova;
		}
		
		ObservableList<Clan> observableListaClanova = FXCollections.observableArrayList(filtriranaListaClanova);
		clanoviTable.setItems(observableListaClanova);
	}
	
	public List<Clan> ucitajClana()
	{
		List<Clan> listaClanova = new ArrayList<Clan>();
		
		String fileName = "clan.txt";
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(new File(fileName)));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		Clan clan = null;
		
		while (true)
		{
			try {
				clan = ucitajClanaIzDatoteke(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (clan == null)
			{
				break;
			}
			
			listaClanova.add(clan);
		}
		
		return listaClanova;
	}
	
	private final Clan ucitajClanaIzDatoteke (BufferedReader reader) throws IOException
	{
		String ime = null, prezime = null, OIB = null;
		
		OIB = reader.readLine();
		if (OIB == null)
		{
			return null;
		}
		prezime = reader.readLine();
		ime = reader.readLine();
		
		Clan clan = new Clan(ime, prezime, OIB);
		return clan;
	}
}