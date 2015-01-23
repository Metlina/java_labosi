package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.glavna.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class GlavnaController {

	public void prikaziKnjige()
	{
		try {
			BorderPane knjigePane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/knjige.fxml"));
			//BorderPane knjigePane = (BorderPane)FXMLLoader.load(new File("src/hr/tvz/java/vjezbe/fxml/knjige.fxml").toURI().toURL());
			Main.setCenterPane(knjigePane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void novaKnjiga()
	{
		try {
			BorderPane knjigaNovaPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/knjigaUredi.fxml"));
			Main.setCenterPane(knjigaNovaPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void prikaziCasopise()
	{
		try {
			BorderPane casopisiPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/casopisi.fxml"));
			Main.setCenterPane(casopisiPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void noviCasopis()
	{
		try {
			BorderPane casopisNovaPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/casopisUredi.fxml"));
			Main.setCenterPane(casopisNovaPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void prikaziClanove()
	{
		try {
			BorderPane clanoviPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/clanovi.fxml"));
			Main.setCenterPane(clanoviPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void noviClan()
	{
		try {
			BorderPane clanNovaPane = (BorderPane)FXMLLoader.load(Main.class.getResource("../fxml/clanUredi.fxml"));
			Main.setCenterPane(clanNovaPane);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
