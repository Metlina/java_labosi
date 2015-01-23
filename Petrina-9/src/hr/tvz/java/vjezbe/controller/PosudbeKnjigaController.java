package hr.tvz.java.vjezbe.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.baza.BazaPodataka;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class PosudbeKnjigaController {
	
	public PosudbeKnjigaController() {}
	
	@FXML
	private TextField nazivPosudbaKnjiga;

	@FXML
	private TableView<Posudba<Knjiga>> posudbaKnjigaTable;
	
	@FXML
	private TableColumn<Posudba<Knjiga>, String> nazivKnjigeColumn;
	
	@FXML
	private TableColumn<Posudba<Knjiga>, String> prezimeKorisnikaColumn;
	
	@FXML
	private TableColumn<Posudba<Knjiga>, String> imeKorisnikaColumn;
	
	@FXML
	private TableColumn<Posudba<Knjiga>, String> datumPosudbeColumn;
	
	@FXML
	public void initialize() 
	{
		nazivKnjigeColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Knjiga>, String> data) 
			{ 
				return new ReadOnlyObjectWrapper<String>(data.getValue().getPublikacija().getNaziv());
			} 
		});
		prezimeKorisnikaColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Knjiga>, String> data)
			{ 
				return new ReadOnlyObjectWrapper<String>(data.getValue().getClan().getPrezime());
			} 
		});
		imeKorisnikaColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Knjiga>, String> data) 
			{ 
				return new ReadOnlyObjectWrapper<String>(data.getValue().getClan().getIme());
			} 
		});
		datumPosudbeColumn.setCellValueFactory(new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() 
		{ 
			@Override
			public ObservableValue<String> call(CellDataFeatures<Posudba<Knjiga>, String> data) 
			{
				return new ReadOnlyObjectWrapper<String>(data.getValue().getLocalDateTIme().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
			} 
		});
	}
	
	public void dohvatiPosudbeKnjige() throws Exception
	{
		List<Posudba<Knjiga>> listaKnjiga = BazaPodataka.dohvatiPosudbaKnjiga();
		
		List<Posudba<Knjiga>> filtriranaListaKnjiga = new ArrayList<Posudba<Knjiga>>();
		
		if (nazivPosudbaKnjiga.getText().isEmpty() == false)
		{
			filtriranaListaKnjiga = listaKnjiga.stream().filter(p -> p.getPublikacija().getNaziv().contains(nazivPosudbaKnjiga.getText())).collect(Collectors.toList());
		}
		else
		{
			filtriranaListaKnjiga = listaKnjiga;
		}
		
		ObservableList<Posudba<Knjiga>> observableListaKnjiga = FXCollections.observableArrayList(filtriranaListaKnjiga);
		posudbaKnjigaTable.setItems(observableListaKnjiga);
	}

}