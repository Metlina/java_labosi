package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.Datoteke;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.glavna.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

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
		List<Clan> listaClanova = Datoteke.ucitajClana();
		
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
	
	public void obrisi() {
		Clan c = clanoviTable.getSelectionModel().getSelectedItem();
		clanoviTable.getItems().remove(c);
		Datoteke.unesiClanove(clanoviTable.getItems());
	}

	public void uredi() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("../fxml/clanUredi.fxml"));
			BorderPane root = (BorderPane) l.load();
			ClanUrediController cont = l.<ClanUrediController> getController();
			cont.urediParametri(clanoviTable.getItems(), clanoviTable.getSelectionModel().getSelectedItem());
			Main.setCenterPane(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}