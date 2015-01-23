package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.enumeracija.Jezik;
import hr.tvz.java.vjezbe.enumeracija.VrstaPublikacije;
import hr.tvz.java.vjezbe.glavna.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
		knjigaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					Knjiga knjiga = (Knjiga) knjigaTable.getSelectionModel().getSelectedItem();
					try {
						FXMLLoader fxmlLoader = new FXMLLoader();
						URL location = ClanoviController.class.getResource("../fxml/clanovi.fxml");
						fxmlLoader.setLocation(location);
						fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
						Parent root = (Parent) fxmlLoader.load(location.openStream());
						ClanoviController controller = (ClanoviController) fxmlLoader.getController();
						controller.setPublikacija(knjiga);
						Stage stage = new Stage();
						stage.setTitle("Odabir èlana za posudbu knjige " + knjiga.getNaziv());
						stage.setScene(new Scene(root, 650, 450));
						stage.show();
						controller.setStage(stage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public void dohvatiKnjige() throws Exception
	{
		//List<Knjiga> listaKnjiga = Datoteke.ucitajKnjige();
		List<Knjiga> listaKnjiga = BazaPodataka.dohvatiKnjige();
		
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

	public void obrisi() {
		Knjiga c = knjigaTable.getSelectionModel().getSelectedItem();
		knjigaTable.getItems().remove(c);
		//Datoteke.unesiKnjige(knjigaTable.getItems());
	}

	public void uredi() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("../fxml/knjigaUredi.fxml"));
			BorderPane root = (BorderPane) l.load();
			KnjigaUrediController cont = l.<KnjigaUrediController> getController();
			cont.urediParametri(knjigaTable.getItems(), knjigaTable.getSelectionModel().getSelectedItem());
			Main.setCenterPane(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}