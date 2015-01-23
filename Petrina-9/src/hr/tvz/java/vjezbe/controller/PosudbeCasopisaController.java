package hr.tvz.java.vjezbe.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Posudba;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class PosudbeCasopisaController {
	
	public PosudbeCasopisaController() {}

	@FXML
	private TextField nazivPosudbaCasopis;

	@FXML
	private TableView<Posudba<Casopis>> posudbaCasopisTable;
	
	@FXML
	private TableColumn<Posudba<Casopis>, String> nazivCasopisaColumn;
	
	@FXML
	private TableColumn<Posudba<Casopis>, String> prezimeKorisnikaColumn;
	
	@FXML
	private TableColumn<Posudba<Casopis>, String> imeKorisnikaColumn;
	
	@FXML
	private TableColumn<Posudba<Casopis>, String> datumPosudbeColumn;
	
	@FXML
	public void initialize() 
	{
		nazivCasopisaColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Casopis>, String> data) 
			{ 
				return new ReadOnlyObjectWrapper<String>(data.getValue().getPublikacija().getNaziv());
			} 
		});
		prezimeKorisnikaColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Casopis>, String> data)
			{ 
				return new ReadOnlyObjectWrapper<String>(data.getValue().getClan().getPrezime());
			} 
		});
		imeKorisnikaColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Casopis>, String> data) 
			{ 
				return new ReadOnlyObjectWrapper<String>(data.getValue().getClan().getIme());
			} 
		});
		datumPosudbeColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Casopis>, String> data) 
			{
				return new ReadOnlyObjectWrapper<String>(data.getValue().getLocalDateTIme().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
			} 
		});
	
	}
	
	public void dohvatiPosudbeCasopisa() throws Exception
	{
		List<Posudba<Casopis>> listaCasopisa = BazaPodataka.dohvatiPosudbaCasopis();
		
		List<Posudba<Casopis>> filtriranaListaCasopisa = new ArrayList<Posudba<Casopis>>();
		
		if (nazivPosudbaCasopis.getText().isEmpty() == false)
		{
			filtriranaListaCasopisa = listaCasopisa.stream().filter(p -> p.getPublikacija().getNaziv().contains(nazivPosudbaCasopis.getText())).collect(Collectors.toList());
		}
		else
		{
			filtriranaListaCasopisa = listaCasopisa;
		}
		
		ObservableList<Posudba<Casopis>> observableListaCasopisa = FXCollections.observableArrayList(filtriranaListaCasopisa);
		posudbaCasopisTable.setItems(observableListaCasopisa);
	}

}