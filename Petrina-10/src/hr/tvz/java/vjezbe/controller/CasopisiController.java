package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.entitet.Casopis;
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
		casopisTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					Casopis casopis = (Casopis) casopisTable.getSelectionModel().getSelectedItem();
					//if (casopis.provjeriRaspolozivost() == false)
					//{
						try {
							FXMLLoader fxmlLoader = new FXMLLoader();
							URL location = ClanoviController.class
									.getResource("../fxml/clanovi.fxml");
							fxmlLoader.setLocation(location);
							fxmlLoader
									.setBuilderFactory(new JavaFXBuilderFactory());
							Parent root = (Parent) fxmlLoader.load(location
									.openStream());
							ClanoviController controller = (ClanoviController) fxmlLoader
									.getController();
							controller.setPublikacija(casopis);
							Stage stage = new Stage();
							stage.setTitle("Odabir �lana za posudbu knjige "
									+ casopis.getNaziv());
							stage.setScene(new Scene(root, 650, 450));
							stage.show();
							controller.setStage(stage);
						} catch (IOException e) {
							e.printStackTrace();
						}
					//}
					/*else 
					{
						Dialogs.create().title("Informacija").message("Casopis je nedostupan").showInformation();
					}*/
				}
			}
		});
	}

	public void dohvatiCasopise() throws Exception
	{		
		//List<Casopis> listaCasopisa = Datoteke.ucitajCasopise();
		List<Casopis> listaCasopisa = BazaPodataka.dohvatiCasopise();
		
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
	
	public void obrisi() {
		Casopis c = casopisTable.getSelectionModel().getSelectedItem();
		casopisTable.getItems().remove(c);
		//Datoteke.unesiCasopise(casopisTable.getItems());
	}

	public void uredi() {
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("../fxml/casopisUredi.fxml"));
			BorderPane root = (BorderPane) l.load();
			CasopisUrediController cont = l.<CasopisUrediController> getController();
			cont.urediParametri(casopisTable.getItems(), casopisTable.getSelectionModel().getSelectedItem());
			Main.setCenterPane(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}