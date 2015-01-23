package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.controller.base.DialogHelper;
import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.entitet.Publikacija;
import hr.tvz.java.vjezbe.glavna.Main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.controlsfx.dialog.Dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class ClanoviController {

	private Publikacija publikacija;
	private Stage stage;
	
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
		clanoviTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (publikacija == null)
					return;
				if (event.getClickCount() > 1) {
					Clan clan = (Clan) clanoviTable.getSelectionModel().getSelectedItem();
					if (publikacija instanceof Knjiga) {
						Posudba<Knjiga> posudba = new Posudba<>(clan,(Knjiga) publikacija, LocalDateTime.now());
						try {
							BazaPodataka.spremiPosudbuKnjige(posudba);
						} catch (Exception e) {
							e.printStackTrace();
							DialogHelper.DatabaseError();
						}
					} else {
						Posudba<Casopis> posudba = new Posudba<>(clan,(Casopis) publikacija, LocalDateTime.now());
						try {
							BazaPodataka.spremiPosudbuCasopisa(posudba);
						} catch (Exception e) {
							e.printStackTrace();
							DialogHelper.DatabaseError();
						}
					}
					Dialogs.create().title("Informacija").message("Posudba je uspješno kreirana.")
							.showInformation();
					stage.close();
				}
			}
		});
	}
	
	public void dohvatiClanove() throws Exception
	{
		//List<Clan> listaClanova = Datoteke.ucitajClana();
		List<Clan> listaClanova = BazaPodataka.dohvatiClanove();
		
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
		//Datoteke.unesiClanove(clanoviTable.getItems());
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

	public Stage getStage()
	{
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}

	public Publikacija getPublikacija() {
		return this.publikacija;
	}

	public void setPublikacija(Publikacija publikacija) {
		this.publikacija = publikacija;
	}
}